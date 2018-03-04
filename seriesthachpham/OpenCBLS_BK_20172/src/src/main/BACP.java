/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.LessOrEqual;
import localsearch.constraints.basic.LessThan;
import localsearch.functions.basic.FuncPlus;
import localsearch.functions.conditionalsum.ConditionalSum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.search.TabuSearch;
import localsearch.selectors.MinMaxSelector;

/**
 *
 * @author ad
 */
public class BACP {

    LocalSearchManager ls;
    ConstraintSystem cs;
    MinMaxSelector mms;

    VarIntLS[] x;       // x[i]                 is period of course i
    int numCourse;
    int numPeriod;
    int[] credits;      // credits[i]           is number credits of course i
    int minCredit;      // minCredit            is min of number credit in 1 period
    int maxCredit;      // maxCredit            is max of number credit in 1 period
    int minCourse;      // minCourse            is min of number course in 1 period
    int maxCourse;      // maxCourse            is max of number course in 1 period
    int numPrerequisites;
    int[] begin;        // begin                is array include course prerequisite 
    int[] end;          // (begin[i], end[i])   is a pair course prequisite

    public void stateModel() {
        System.out.println("\nModeling started");
        ls = new LocalSearchManager();
        cs = new ConstraintSystem(ls);

        // Variables
        for (int i = 0; i < numCourse; ++i) {
            x[i] = new VarIntLS(ls, 0, numPeriod - 1);
        }

        // Invariants
        ConditionalSum[] numCreditOfPeriod = new ConditionalSum[numPeriod];
        for (int i = 0; i < numPeriod; ++i) {
            // number credit of period i
            // is equal sum of credits[j] if x[j] == i
            numCreditOfPeriod[i] = new ConditionalSum(x, credits, i);
        }
        ConditionalSum[] numCourseOfPeriod = new ConditionalSum[numPeriod];
        for (int i = 0; i < numPeriod; ++i) {
            // number course of period i
            // is equal sum of 1 if x[j] == i
            numCourseOfPeriod[i] = new ConditionalSum(x, i);
        }

        // Constraints
        // constraint number course in 1 period
        for (int i = 0; i < numPeriod; ++i) {
            cs.post(new LessOrEqual(minCourse, numCourseOfPeriod[i]));
            cs.post(new LessOrEqual(numCourseOfPeriod[i], maxCourse));
        }

        // constraint number credit in 1 period
        for (int i = 0; i < numPeriod; ++i) {
            cs.post(new LessOrEqual(minCredit, numCreditOfPeriod[i]));
            cs.post(new LessOrEqual(numCreditOfPeriod[i], maxCredit));
        }
        
        // constraint prerequisite relationship
        for(int i = 0; i < numPrerequisites; ++i){
            cs.post(new LessThan(x[begin[i] - 1], x[end[i] - 1]));
        }
        
        ls.close();
        System.out.println("Modeling finish");
        System.out.println("Init violation = " + cs.violations());

        mms = new MinMaxSelector(cs);
    }

    public void tabuSearch(IConstraint S, int tabuLen, int maxTime, int maxIter, int maxStable) {
        System.out.println("Start Tabu Search");
        TabuSearch tbs = new TabuSearch();
        tbs.search(S, tabuLen, maxTime, maxIter, maxStable);
        printSolution(x);
    }

    public void printSolution(VarIntLS[] x) {
        System.out.println("============= Solution =============");
        for (int i = 0; i < x.length; ++i) {
            System.out.print(x[i].getValue() + " ");
        }
        System.out.println("\n====================================");
    }

    public void readFile(String pathFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathFile));

            String[] firstLine = br.readLine().split(" ");
            numCourse = Integer.parseInt(firstLine[0]);
            numPeriod = Integer.parseInt(firstLine[1]);

            String[] secondLine = br.readLine().split(" ");
            minCredit = Integer.parseInt(secondLine[0]);
            maxCredit = Integer.parseInt(secondLine[1]);

            String[] thirdLine = br.readLine().split(" ");
            minCourse = Integer.parseInt(thirdLine[0]);
            maxCourse = Integer.parseInt(thirdLine[1]);

            String[] fourthLine = br.readLine().split(" ");
            credits = new int[numCourse];
            x = new VarIntLS[numCourse];
            for (int i = 0; i < numCourse; ++i) {
                credits[i] = Integer.parseInt(fourthLine[i]);
            }

            String fifthLine = br.readLine();
            numPrerequisites = Integer.parseInt(fifthLine);
            begin = new int[numPrerequisites];
            end = new int[numPrerequisites];
            for (int i = 0; i < numPrerequisites; ++i) {
                String[] line = br.readLine().split(" ");
                begin[i] = Integer.parseInt(line[0]);
                end[i] = Integer.parseInt(line[1]);
            }

            br.close();

            System.out.println("\n======= Read file done =======");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showInput() {
        System.out.println("\n======= Input =======");

        System.out.println("Number courses:  " + numCourse);
        System.out.println("Number periods:  " + numPeriod);
        System.out.println("Min credit of 1 period: " + minCredit);
        System.out.println("Max credit of 1 period: " + maxCredit);
        System.out.println("Min course of 1 period: " + minCourse);
        System.out.println("Max course of 1 period: " + maxCourse);
        System.out.print("Credits:    ");
        for (int i = 0; i < numCourse; ++i) {
            System.out.print(credits[i] + " ");
        }
        System.out.print("\nNumber prerequisite:    " + numPrerequisites);
        for (int i = 0; i < numPrerequisites; ++i) {
            if (i % 10 == 0) {
                System.out.println("");
            }
            System.out.print("(" + begin[i] + "," + end[i] + ") ");
        }

        System.out.println("\n======= Input =======");
    }

    public static void main(String[] args) {
        BACP bacp = new BACP();
        bacp.readFile("src\\data\\BACP\\bacp.in01");
        bacp.showInput();
        bacp.stateModel();
        bacp.tabuSearch(bacp.cs, 100, 1000, 10000, 1000);
    }
}
