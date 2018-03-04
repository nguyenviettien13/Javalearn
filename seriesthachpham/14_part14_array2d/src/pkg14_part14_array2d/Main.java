/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg14_part14_array2d;

/**
 *
 * @author NguyenVietTien
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] array2d = new int[10][10];
        for(int i = 0; i <10; i++){
            for(int j = 0; j <10; j++){
                array2d[i][j] = (i+j)%11;
            }
        }
        
        for(int i = 0; i <10; i++){
            for(int j = 0; j <10; j++){
                System.out.print("  " + array2d[i][j]);
            }
            System.out.println("");
        }
    }
}
