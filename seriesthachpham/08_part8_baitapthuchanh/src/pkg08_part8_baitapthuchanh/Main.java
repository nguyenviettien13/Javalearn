/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg08_part8_baitapthuchanh;

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
        System.out.println("    Moi ban nhap lua chon cua minh");
        System.out.println("    1.Tam Giac");
        System.out.println("    2.Hinh Vuong");
        Scanner in = new Scanner(System.in);
        System.out.println("Moi ban nhap: ");
        String op = in.next();
        switch(op){
            case "1":
                System.out.println("   *   ");
                System.out.println("  ***  ");
                System.out.println(" *****");
                break;
            case "2":
                System.out.println("*****");
                System.out.println("*****");
                System.out.println("*****");
                break;
            default:
                System.out.println("Deo co hinh nao nhu the!!!");
                      
        }
                
    }
    
}
