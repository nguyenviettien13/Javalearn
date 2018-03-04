/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Random;
import localsearch.constraints.alldifferent.AllDifferent;
import localsearch.model.ConstraintSystem;
import localsearch.model.IConstraint;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.search.TabuSearch;
import localsearch.selectors.MinMaxSelector;

/**
 *
 * @author ad
 */
public class Sudoku {
    int boardSize = 9;
    LocalSearchManager ls;
    ConstraintSystem cs;
    VarIntLS[][] x;
    VarIntLS[] row;
    VarIntLS[] col;
    
    public void modeling(){
        //============= Modeling =============
        ls = new LocalSearchManager();
        cs = new ConstraintSystem(ls);

        x = new VarIntLS[boardSize][boardSize];
        row = new VarIntLS[boardSize];       // contain 1 row
        col = new VarIntLS[boardSize];       // contain 1 column
        int numRegion = (int) (Math.sqrt(boardSize));
        int regionSize = (int) (boardSize / numRegion);
        
        if (numRegion * numRegion != boardSize) {
            return;
        }
        VarIntLS[] region = new VarIntLS[regionSize * regionSize];    // contain 1 region

        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                x[i][j] = new VarIntLS(ls, 1, boardSize);
                x[i][j].setValue(j + 1);
//                row[j] = x[i][j];
            }
            // add constraint different in a row
//            cs.post(new AllDifferent(row));
        }

        // add constraint different in a column
        for (int j = 0; j < boardSize; ++j) {
            for (int i = 0; i < boardSize; ++i) {
                col[i] = x[i][j];
            }
            cs.post(new AllDifferent(col));
        }

        // add constraint different in a region
        // loop through region
        for (int i = 0; i < numRegion; ++i) {
            for (int j = 0; j < numRegion; ++j) {
                for (int idx = 0; idx < regionSize * regionSize; ++idx) {
                    int indexRow = i * regionSize + idx / regionSize;
                    int indexCol = j * regionSize + idx % regionSize;
//                    System.out.println("region " + i + "-" + j + "-" + indexRow + "-" + indexCol);
                    region[idx] = x[indexRow][indexCol];
                }
                cs.post(new AllDifferent(region));
            }
        }

        ls.close();
        System.out.println("Modeling done");
        System.out.println("Init solution");
        this.printSolution(x);
        System.out.println("Init violation = " + cs.violations());
    }

    public void hillClimbing() {
        System.out.println("Start Hill Climbing");
        
        MinMaxSelector mms = new MinMaxSelector(cs);

        //============= Searching =============
        // In 1 iter: loop through rows, find 2 position which in 1 row and best local swap order to swap
        int iter = 0, maxIter = 1000000;
        int delta, minDelta;
        int selRow = 0, selLeft = 0, selRight = 0;
        Random R = new Random();
        VarIntLS leftSwap = null, rightSwap = null;
        ArrayList<SwapMove> listCandidateMove = new ArrayList<>();

        while (iter < maxIter && cs.violations() > 0) {
            iter++;
            listCandidateMove.clear();
            minDelta = Integer.MAX_VALUE;
            for (int i = 0; i < boardSize; ++i) {
                for (int left = 0; left < boardSize - 1; ++left) {
                    for (int right = left + 1; right < boardSize; ++right) {
                        delta = cs.getSwapDelta(x[i][left], x[i][right]);
                        if (delta < minDelta) {
                            minDelta = delta;
//                            selRow = i;
//                            selLeft = left;
//                            selRight = right;
                            listCandidateMove.clear();
                            listCandidateMove.add(new SwapMove(i, left, right));
                        } else if (delta == minDelta) {
                            listCandidateMove.add(new SwapMove(i, left, right));
                        }

                    }
                }
            }
            if (minDelta < 0) {
                // random local move in list candidate
                int selectedIndex = R.nextInt(listCandidateMove.size());
                SwapMove localMove = listCandidateMove.get(selectedIndex);
                selRow = localMove.getRow();
                selLeft = localMove.getLeft();
                selRight = localMove.getRight();
            } else {
                // random move
                selRow = R.nextInt(boardSize);
                selLeft = R.nextInt(boardSize);
                selRight = R.nextInt(boardSize);
                while(selRight == selLeft){
                    selRight = R.nextInt(boardSize);
                }
            }

            leftSwap = x[selRow][selLeft];
            rightSwap = x[selRow][selRight];
            x[selRow][selLeft].swapValuePropagate(x[selRow][selRight]);
            System.out.println("min delta = " + minDelta + " Swap: (" + selRow + "," + selLeft + "," + selRight + "); Value swap:"
                    + leftSwap.getValue() + "--" + rightSwap.getValue());

            System.out.println("Iter " + iter + ", violation = " + cs.violations());
        }

        System.out.println("Final Iter " + iter + ", violation = " + cs.violations());
        printSolution(x);
    }

    public void tabuSearch(IConstraint S, int tabuLen, int maxTime, int maxIter, int maxStable){
        System.out.println("Start Tabu Search");
        TabuSearch tbs = new TabuSearch();
        tbs.search(S, tabuLen, maxTime, maxIter, maxStable);
        printSolution(x);
    }
    
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.modeling();
//        sudoku.hillClimbing();
        sudoku.tabuSearch(sudoku.cs, 100, 1000, 10000, 10000);
    }

    public void printSolution(VarIntLS[][] x) {
        System.out.println("\nSolution\n");
        int size = x[0].length;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < x[i].length; ++j) {
                System.out.print(x[i][j].getValue() + " ");
            }
            System.out.println("\n");
        }
    }
}

class SwapMove {

    private int left;
    private int right;
    private int row;

    public SwapMove(int row, int left, int right) {
        this.left = left;
        this.right = right;
        this.row = row;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getRow() {
        return row;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
