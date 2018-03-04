/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05_part5_caulenhif;

import java.util.Scanner;

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
        int a, b;
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b = in.nextInt();
        if (a > b) {
            System.out.println("a > b");
        } else if( a < b){
            System.out.println("a < b");
        }else {
            System.out.println("a = b");
        }
    }
}
