/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import localsearch.constraints.alldifferent.AllDifferent;
import localsearch.functions.basic.FuncMinus;
import localsearch.functions.basic.FuncPlus;
import localsearch.model.ConstraintSystem;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.selectors.MinMaxSelector;

/**
 *
 * @author ad
 */
public class QueenHillClimbing {

    public void runAlgo() {
        int numQueen = 237;

        LocalSearchManager ls = new LocalSearchManager();
        ConstraintSystem cs = new ConstraintSystem(ls);

        //================== Modeling =======================
        // init variable
        // x[i] - row of queen whose column is i
        VarIntLS[] x = new VarIntLS[numQueen];
        for (int i = 0; i < numQueen; ++i) {
            x[i] = new VarIntLS(ls, 0, numQueen - 1);
        }

        // add constraints
        // constraint x[i] != x[j]
        cs.post(new AllDifferent(x));

        // constraint (x[i] + i) != (x[j] + j)
        IFunction[] FPlus = new IFunction[numQueen];
        for (int i = 0; i < numQueen; ++i) {
            FPlus[i] = new FuncPlus(x[i], i);
        }
        cs.post(new AllDifferent(FPlus));

        // constraint (x[i] - i) != (x[j] - j)
        IFunction[] FMinus = new IFunction[numQueen];
        for (int i = 0; i < numQueen; ++i) {
            FMinus[i] = new FuncPlus(x[i], -i);
        }
        cs.post(new AllDifferent(FMinus));

        ls.close();

        System.out.println("Init vio = " + cs.violations());

        //================== Searching =======================
        MinMaxSelector mms = new MinMaxSelector(cs);
        int iter = 0, minDelta, delta, sel_value = 0;
        int maxIter = 100000;
        VarIntLS sel_x = new VarIntLS(ls, 0, numQueen);
        ArrayList<LocalMove> listCandidateLocalMove = new ArrayList<>();
        Random rand = new Random();

        while (iter < maxIter && cs.violations() > 0) {
            listCandidateLocalMove.clear();
            iter++;
//            sel_x = mms.selectMostViolatingVariable();
//            sel_value = mms.selectMostPromissingValue(sel_x);
//            minDelta = cs.getAssignDelta(sel_x, sel_value);
//            listCandidateLocalMove.add(new LocalMove(sel_x, sel_value));

            minDelta = Integer.MAX_VALUE;
            // find (x[i], value) pair for best local move
            for (int i = 0; i < numQueen; ++i) {
                for (int j = x[i].getMinValue(); j <= x[i].getMaxValue(); ++j) {
//                int j = mms.selectMostPromissingValue(x[i]);
                    if (j != x[i].getValue()) {

                        delta = cs.getAssignDelta(x[i], j);
                        if (delta < minDelta) {
//                            sel_x = x[i];
//                            sel_value = j;
                            minDelta = delta;
                            listCandidateLocalMove.clear();
                            listCandidateLocalMove.add(new LocalMove(x[i], j));
                        }else if(delta == minDelta){
                            listCandidateLocalMove.add(new LocalMove(x[i], j));
                        }
                    }
                }
            }
            if(minDelta < 0){
                // random local move in list candidate
                int selectedIndex = rand.nextInt(listCandidateLocalMove.size());
                LocalMove localMove = listCandidateLocalMove.get(selectedIndex);
                sel_x = localMove.getX();
                sel_value = localMove.getVal();
            }else{
                sel_x= x[rand.nextInt(numQueen)];
                sel_value= rand.nextInt(numQueen);
            }
//            int sel_idx = rand.nextInt(listCandidateLocalMove.size());
//            sel_x = listCandidateLocalMove.get(sel_idx).getX();
//            sel_value = listCandidateLocalMove.get(sel_idx).getVal();

            // local move
            sel_x.setValuePropagate(sel_value);
            System.out.println("Iter " + iter + ", vio = " + cs.violations());
        }

        System.out.println("\nFinal iter " + iter + ", Final violation = " + cs.violations());
//        printHTML("output-queen-" + numQueen + ".html", x);
    }

    public void printHTML(String pathFile, VarIntLS[] x) {
        int numQueen = x.length;
        StringBuilder str = new StringBuilder();
        str.append("<body><table>");

        for (int i = 0; i < numQueen; ++i) {
            str.append("<tr>");
            for (int j = 0; j < numQueen; ++j) {
                if (x[j].getValue() == i) {
                    str.append("<td width='20' height='20' bgcolor='red'></td>");
                } else {
                    str.append("<td width='20' height='20' bgcolor='blue'></td>");

                }
            }
            str.append("</tr>");

        }
        str.append("</table></body>");

        // write to file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile));
            bw.write(str.toString());
            bw.close();
            System.out.println("\nPrint HTML successfull");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        QueenHillClimbing QHC = new QueenHillClimbing();
        QHC.runAlgo();
    }
}

class LocalMove {

    private VarIntLS x;
    private int val;

    public LocalMove(VarIntLS x, int val) {
        this.x = x;
        this.val = val;
    }

    public VarIntLS getX() {
        return x;
    }

    public int getVal() {
        return val;
    }

    public void setX(VarIntLS x) {
        this.x = x;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
