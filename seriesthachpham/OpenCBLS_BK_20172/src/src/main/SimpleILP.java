/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.LessOrEqual;
import localsearch.constraints.basic.LessThan;
import localsearch.functions.basic.FuncPlus;
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
public class SimpleILP {
    LocalSearchManager ls;
    ConstraintSystem cs;
    MinMaxSelector mms;
    VarIntLS[] x;
    
    public void modeling(){
        System.out.println("Modeling started");
        ls = new LocalSearchManager();
        cs = new ConstraintSystem(ls);
        
        
        int numVar = 4;
        x = new VarIntLS[numVar];
        for(int i = 0; i < numVar; ++i){
            x[i] = new VarIntLS(ls, 0, 9);
        }
        
        // add constraint 1: x1 + x2 + x3 = 9
        IFunction f11 = new FuncPlus(x[0], x[2]);
        IFunction f12 = new FuncPlus(f11, x[1]);
        cs.post(new IsEqual(f12, 9));
        
        // add constraint 2: x2 >= x3
        cs.post(new LessOrEqual(x[2], x[1]));
        
        // add constraint 3: x1 + x3 = x2 + x4
        IFunction f31 = new FuncPlus(x[1], x[3]);
        cs.post(new IsEqual(f31, f11));
        
        // add constraint 4: if x1 >= x2 => x3 + x4 = 7
        IConstraint cs41 = new LessOrEqual(x[1], x[0]);
        IConstraint cs42 = new IsEqual(new FuncPlus(x[2], x[3]), 7);
        cs.post(new Implicate(cs41, cs42));
        
        ls.close();
        System.out.println("Modeling finish");
        System.out.println("Init violation = " + cs.violations());
     
        mms = new MinMaxSelector(cs);
    }
    
    public void tabuSearch(IConstraint S, int tabuLen, int maxTime, int maxIter, int maxStable){
        System.out.println("Start Tabu Search");
        TabuSearch tbs = new TabuSearch();
        tbs.search(S, tabuLen, maxTime, maxIter, maxStable);
        printSolution(x);
    }
    
    public void printSolution(VarIntLS[] x){
        System.out.println("============= Solution =============");
        for(int i = 0; i < x.length; ++i){
            System.out.print(x[i].getValue() + " ");
        }
        System.out.println("\n====================================");
    }
    
    public static void main(String[] args) {
        SimpleILP ilp = new SimpleILP();
        ilp.modeling();
        ilp.tabuSearch(ilp.cs, 100, 1000, 10000, 1000);
    }
}
