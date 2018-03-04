package main.ver2;

import java.io.*;
import java.util.*;

import localsearch.model.*;
import localsearch.search.TabuSearch;
import localsearch.constraints.basic.*;
import localsearch.functions.basic.FuncPlus;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import localsearch.functions.basic.FuncMinus;
import localsearch.functions.conditionalsum.ConditionalSum;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

class Busy {

    public int day;
    public int slot;

    public Busy(int day, int slot) {
        this.day = day;
        this.slot = slot;
    }
}

class Course {

    public int courseID;
    public String name;
    public int instructorID;
    public int nbSlots;
    public int nbStudents;
    public ArrayList<String> classes;

    public Course(int ID, String name, int instructorID, int nbSlots, int nbStudents,
            ArrayList<String> classes) {
        this.courseID = ID;
        this.name = name;
        this.instructorID = instructorID;
        this.nbSlots = nbSlots;
        this.nbStudents = nbStudents;
        this.classes = classes;
    }
}

class Instructor {

    public int instructorID;
    public int name;
    public ArrayList<Busy> busyList;

    public Instructor(int ID, int name, ArrayList<Busy> busyList) {
        this.instructorID = ID;
        this.name = name;
        this.busyList = busyList;
    }
}

class Room {

    public int roomID;
    public String roomName;
    public int place;

    public Room(int roomID, String roomName, int place) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.place = place;
    }

}

public class TimeTablingWithRoom {

    // data input
    Course[] courses;
    Instructor[] instructors;
    Room[] rooms;

    // model
    LocalSearchManager ls;
    ConstraintSystem S;
    VarIntLS[] x;
    VarIntLS[] x_d;     // day of course i
    VarIntLS[] x_s;     // start slot of course i
    VarIntLS[] x_r;     // room of course i
    // solution store
    int[] vx;

    Random R;

    public TimeTablingWithRoom() {

    }

    public void readData(String fn) {

        try {
            File file = new File(fn);
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbFactory = d.newDocumentBuilder();

            Document doc = dbFactory.parse(file);
            Element node = (Element) doc.getElementsByTagName("timetabling")
                    .item(0);

            Element tmp = (Element) node
                    .getElementsByTagName("instructorsList").item(0);
            NodeList instructorsList = tmp.getElementsByTagName("instructor");

            NodeList coursesList = node.getElementsByTagName("course");
            NodeList roomsList = node.getElementsByTagName("room");
            // System.out.println(""+node.getChildNodes().item(3).getNodeName());

            // nin = instructorsList.getLength();
            // nco = coursesList.getLength();
            instructors = new Instructor[instructorsList.getLength()];
            courses = new Course[coursesList.getLength()];
            rooms = new Room[roomsList.getLength()];

            // System.out.println("nin =" + nin + ",nco= " + nco);
            // read IntructorList
            for (int i = 0; i < instructors.length; i++) {
                Node no = instructorsList.item(i);
                // System.out.println("i= "+i );
                if (no.getNodeType() == Node.ELEMENT_NODE) {
                    Element in = (Element) no;
                    String sid = in.getElementsByTagName("id").item(0)
                            .getTextContent();
                    String sname = in.getElementsByTagName("name").item(0)
                            .getTextContent();
                    int id = Integer.parseInt(sid);
                    int name = Integer.parseInt(sname);
                    // System.out.println("id= "+id+",name= "+sname);
                    NodeList b = in.getElementsByTagName("busy");
                    ArrayList<Busy> bb = new ArrayList();
                    for (int j = 0; j < b.getLength(); j++) {
                        Element cl = (Element) b.item(j);
                        Busy busy = new Busy(Integer.parseInt(cl
                                .getElementsByTagName("day").item(0)
                                .getTextContent()) - 2, Integer.parseInt(cl
                                        .getElementsByTagName("slot").item(0)
                                        .getTextContent()) - 1);

                        bb.add(busy);
                        System.out.println("" + busy.day + " " + busy.slot);
                    }
                    instructors[i] = new Instructor(id, name, bb);
                    // System.out.println(" "+instructors[i].getId() );
                }
            }
            // read coursesList
            for (int i = 0; i < courses.length; i++) {
                Node no = coursesList.item(i);
                if (no.getNodeType() == Node.ELEMENT_NODE) {
                    Element co = (Element) no;
                    String sid = co.getElementsByTagName("id").item(0)
                            .getTextContent();
                    String sname = co.getElementsByTagName("name").item(0)
                            .getTextContent();
                    String sinstructor = co.getElementsByTagName("instructor")
                            .item(0).getTextContent();
                    String sSlot = co.getElementsByTagName("nbrSlots").item(0)
                            .getTextContent();
                    String sStudent = co.getElementsByTagName("nbrStudents").item(0)
                            .getTextContent().trim();
                    int id = Integer.parseInt(sid);
                    int instructor = Integer.parseInt(sinstructor);
                    int slot = Integer.parseInt(sSlot);
                    int nbStudent = Integer.parseInt(sStudent);
                    NodeList cl = co.getElementsByTagName("class");
                    ArrayList<String> c = new ArrayList();

                    // System.out.println("" + instructor);
                    for (int j = 0; j < cl.getLength(); j++) {
                        Element cla = (Element) cl.item(j);
                        c.add(cla.getTextContent());
                        // System.out.println(""+cla.getTextContent());
                    }
                    courses[i] = new Course(id, sname, instructor, slot, nbStudent, c);
                }
            }

            // read room list
            for (int i = 0; i < rooms.length; ++i) {
                Node no = roomsList.item(i);
                if (no.getNodeType() == Node.ELEMENT_NODE) {
                    Element ro = (Element) no;
                    String sid = ro.getElementsByTagName("id").item(0).getTextContent().trim();
                    String sname = ro.getElementsByTagName("name").item(0).getTextContent().trim();
                    String spl = ro.getElementsByTagName("place").item(0).getTextContent().trim();
                    int id = Integer.parseInt(sid);
                    int pl = Integer.parseInt(spl);
                    rooms[i] = new Room(id, sname, pl);
                    System.out.println(id + "-" + sname + "-" + pl);
                }
            }

            vx = new int[courses.length];
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean commonClass(Course c1, Course c2) {
        // Tra ve true neu 2 course co chung it nhat 1 lop
        for (int i = 0; i < c1.classes.size(); i++) {
            String clsi = c1.classes.get(i);
            for (int j = 0; j < c2.classes.size(); j++) {
                String clsj = c2.classes.get(j);
                if (clsi.equals(clsj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void stateModel() {
        System.out.println("======= Modeling started =======");
        ls = new LocalSearchManager();
        S = new ConstraintSystem(ls);

        // Variables
        x_d = new VarIntLS[courses.length];
        x_s = new VarIntLS[courses.length];
        x_r = new VarIntLS[courses.length];
        for (int i = 0; i < courses.length; ++i) {
            x_d[i] = new VarIntLS(ls, 0, 4);
            x_s[i] = new VarIntLS(ls, 0, 6 - courses[i].nbSlots);
            // find set rooms appropriate with course i about place constraint
            Set<Integer> setRoomID = new HashSet<>();
            for (int j = 0; j < rooms.length; ++j) {
                if (rooms[j].place >= courses[i].nbStudents) {
                    setRoomID.add(j);
                }
            }
            x_r[i] = new VarIntLS(ls, setRoomID);
        }

        // Constraints
        for (int i = 0; i < courses.length - 1; ++i) {
            for (int j = i + 1; j < courses.length; ++j) {
                // add constraint not overlap if 2 courses conflict about common professor or class
                boolean conflict = courses[i].instructorID == courses[j].instructorID;
                conflict = conflict || commonClass(courses[i], courses[j]);
                if (conflict) {
                    S.post(new Implicate(
                            new IsEqual(x_d[i], x_d[j]),
                            new NotOverLap(x_s[i], courses[i].nbSlots, x_s[j], courses[j].nbSlots))
                    );
                }
                // add constraint not overlap if 2 course has common room
                S.post(new Implicate(
                        new IsEqual(x_r[i], x_r[j]),
                        new NotOverLap(x_s[i], courses[i].nbSlots, x_s[j], courses[j].nbSlots))
                );
            }
        }
        // constraint busy
        HashMap<Integer, Instructor> mapIDInstructor = new HashMap<>();
        for (int i = 0; i < instructors.length; ++i) {
            mapIDInstructor.put(instructors[i].instructorID, instructors[i]);
        }
        for (int i = 0; i < courses.length; ++i) {
            ArrayList<Busy> busyList = mapIDInstructor.get(courses[i].instructorID).busyList;
            for (int j = 0; j < busyList.size(); ++j) {
                Busy busy = busyList.get(j);
                for (int k = 0; k < courses[i].nbSlots; ++k) {
                    S.post(new Implicate(
                            new IsEqual(x_d[i], busy.day),
                            new NotEqual(x_s[i], busy.slot - k)
                    ));
                }
            }
        }

        ls.close();
        System.out.println("Init violation = " + S.violations());
        System.out.println("======= Modeling done =======");
    }

    public void stateModelOld() {
        R = new Random();

        ls = new LocalSearchManager();
        x = new VarIntLS[courses.length];
        for (int i = 0; i < courses.length; i++) {
            x[i] = new VarIntLS(ls, 0, 29 - courses[i].nbSlots + 1);

        }
        // init x[i]
        S = new ConstraintSystem(ls);
        for (int i = 0; i < courses.length; i++) {
            int a = 0;
            do {
                a = R.nextInt(30);
            } while (a % 6 + courses[i].nbSlots > 6); // check constraint xs(c)+
            // d(c)< p+1
            x[i].setValuePropagate(a);
        }

        // busy instructor
        for (int i = 0; i < courses.length; i++) {
            ArrayList<Busy> busy = new ArrayList<>();
            for (int k = 0; k < instructors.length; k++) {
                if (instructors[k].instructorID == courses[i].instructorID) {
                    busy = instructors[k].busyList;
                    for (int j = 0; j < busy.size(); j++) {

                        Busy b = busy.get(j);
                        int p = (b.day - 1) * 6 + b.slot;
                        for (int m = 0; m < courses[i].nbSlots; m++) {
                            S.post(new NotEqual(x[i], p - m)); // x[i]+m !=p <=>
                            // x[i]!= p-m
                        }
                    }
                }
            }

        }

        // // xs(c)+d(c)<p+1 check while setvalueprogate to x[i] and search
        // constraint p(c1)=p(c2)
        for (int i = 0; i < courses.length; i++) {
            for (int j = 0; j < courses.length; j++) {
                if (i != j
                        && courses[i].instructorID == courses[j].instructorID) // courses[i]
                // and
                // courses[j]
                // cos
                // cung
                // professor
                // teach
                {
                    S.post(new NotOverLap(x[i], courses[i].nbSlots, x[j],
                            courses[j].nbSlots));
                }
            }
        }
        // //constraint cl1=cl2
        for (int i = 0; i < courses.length; i++) {
            for (int j = 0; j < courses.length; j++) {
                if (i != j) {
                    ArrayList<String> cl1 = courses[i].classes;
                    ArrayList<String> cl2 = courses[j].classes;
                    for (int m = 0; m < cl1.size(); m++) {
                        for (int n = 0; n < cl2.size(); n++) {
                            if (cl1.get(m).equals(cl2.get(n))) // course[i] va
                            // mon course co
                            // chung lop
                            {
                                S.post(new NotOverLap(x[i], courses[i].nbSlots,
                                        x[j], courses[j].nbSlots));
                            }
                        }
                    }

                }
            }
        }
        ls.close();
    }

    public boolean search() {
        localsearch.search.TabuSearch ts = new localsearch.search.TabuSearch();
        ts.search(S, 30, 20, 100000, 50);

        for (int i = 0; i < courses.length; i++) {
            vx[i] = x_d[i].getValue() * 6 + x_s[i].getValue();
        }
        System.out.println("Result S = " + S.violations());
        return S.violations() == 0;
    }

    public void printSolutionHTML(String fn) {
        ArrayList<String> cl = new ArrayList<>();
        for (int i = 0; i < courses.length; i++) {
            ArrayList<String> cl1 = new ArrayList<>();
            cl1 = courses[i].classes;
            for (int j = 0; j < cl1.size(); j++) {
                if (cl.indexOf(cl1.get(j)) == -1) {
                    cl.add(cl1.get(j));
                }
            }
        }
        try {

            File file = new File(fn);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>");
            bw.write("<body><table BORDER=1 \"WIDTH:960px\" BGCOLOR=\"white\" align=center><tr><th colspan=32>Thời khóa biểu</th></tr>");
            // in cac muc
            bw.write("<tr>");
            bw.write("<TH ROWSPAN =2 colspan=2 BGCOLOR=\"gray\" \"> Lớp </ TH>");

            for (int i = 2; i < 7; i++) {
                bw.write("<th colspan=\"6\" STYLE=\"WIDTH:180px\" BGCOLOR=\"gray\">Thứ "
                        + i + "</th>");
            }
            bw.write("</tr>");
            bw.write("<tr>");
            for (int i = 0; i < 30; i++) {
                bw.write("<td colspan=\"1\" STYLE=\"WIDTH:30px \" BGCOLOR=\"gray\">tiết "
                        + (i % 6 + 1) + "</td>");
            }

            bw.write("</tr>");
            // in ra tiet hoc cac lop
            for (int i = 0; i < cl.size(); i++) {
                // System.out.println("Lớp : " + cl.get(i));
                bw.write("<tr><td colspan=2 BGCOLOR=\"gray\">" + cl.get(i)
                        + "</td>");
                int last = 0;
                for (int t = 0; t < 30; t++) {
                    if ((t) % 6 == 0 && last != t || (t == 29 && t == last)) {
                        bw.write("<td STYLE=\"WIDTH:" + ((t - last) * 30)
                                + "px\" \" colspan=" + (t - last) + "></td>");
                        last = t;
                    }
                    for (int j = 0; j < courses.length; j++) {
                        //if (t == x[j].getValue()) {
                        if (t == vx[j]) {
                            ArrayList cl1 = courses[j].classes;
                            for (int k = 0; k < cl1.size(); k++) {
                                if (cl.get(i).equals(cl1.get(k))) {
                                    if (t != last) {
                                        bw.write("<td STYLE=\"WIDTH:"
                                                + ((t - last) * 30)
                                                + "px\" \" colspan="
                                                + (t - last) + "></td>");
                                    }
                                    bw.write("<td STYLE=\"WIDTH:"
                                            + courses[j].nbSlots
                                            + "px\" BGCOLOR=\"lightgray\" colspan="
                                            + courses[j].nbSlots + ">"
                                            + courses[j].name
                                            + " ,Giảng viên:  "
                                            + courses[j].instructorID
                                            + " ,Phòng: "
                                            + rooms[x_r[j].getValue()].roomName + ", ("
                                            + courses[j].nbStudents + "/"
                                            + rooms[x_r[j].getValue()].place + ")"
                                            + "</td>");
                                    last = t + courses[j].nbSlots;
                                    break;
                                }
                            }

                        }
                    }

                }
                bw.write("</tr> ");
            }
            bw.write("</table></body></html>");
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testBatch(String filename, int nbTrials) {
        TimeTablingWithRoom TT = new TimeTablingWithRoom();
        TT.readData(filename);
        double[] t = new double[nbTrials];
        double avg_t = 0;
        int nbSolved = 0;
        for (int k = 0; k < nbTrials; k++) {
            double t0 = System.currentTimeMillis();
            TT.stateModel();
            boolean ok = TT.search();
            if (ok) {
                t[k] = (System.currentTimeMillis() - t0) * 0.001;
                avg_t += t[k];
                nbSolved++;
            }
        }
        avg_t = avg_t * 1.0 / nbSolved;
        System.out.println("Time = " + avg_t + ", nbSolved = " + nbSolved);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        TimeTablingWithRoom TT = new TimeTablingWithRoom();
        TT.readData("src\\data\\SIETimeTabling\\timetabling-data-10-10_room.xml");
        TT.stateModel();
        TT.search();

        Date d = new Date();

        TT.printSolutionHTML("TimeTabling-10-10-" + d.getTime() + ".html");

        //TimeTabling TT = new TimeTabling();
        //TT.testBatch("data\\SIETimeTabling\\timetabling-data-46-46.xml",10);
    }

}
