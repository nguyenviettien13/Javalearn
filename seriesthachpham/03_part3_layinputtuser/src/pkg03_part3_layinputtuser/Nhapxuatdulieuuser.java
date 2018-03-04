/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_part3_layinputtuser;

import java.util.Scanner;

/**
 *
 * @author NguyenVietTien
 */
public class Nhapxuatdulieuuser {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int tuoi = sc.nextInt();
        System.out.println("So ban vua nhap vao la: " +tuoi);  
        
        String ten = sc.nextLine();
        System.out.println("Ten cua ban la: " + ten);
        sc.nextLine();
        
        System.out.println("Làm toán với phép cộng");
        int a, b;
        System.out.print("Nhập vào số nguyên thứ nhất: ");
        a = sc.nextInt();
        System.out.print("Nhập vào số nguyên thứ hai:   ");
        b = sc.nextInt();
        System.out.println("stt1 + stt2 = " + (a+b));
    }
    
    
}
