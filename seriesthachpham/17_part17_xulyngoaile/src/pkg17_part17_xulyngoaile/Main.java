/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg17_part17_xulyngoaile;

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
        boolean thanhcong = false;
        do {
            try{
            Scanner sc = new Scanner(System.in);
            int a, b;
            System.out.println("Nhap vao so a: ");
            a = sc.nextInt();
            System.out.println("Nhap vao so b: ");
            b = sc.nextInt();
            System.out.println("Thương của phép chia a/ b la : " + (double)a/b);
            thanhcong = true;
        } catch(Exception e){
            System.out.println("Da xay ra loi, loi chia cho 0");
        }
        } while (thanhcong== false);
    }
}
