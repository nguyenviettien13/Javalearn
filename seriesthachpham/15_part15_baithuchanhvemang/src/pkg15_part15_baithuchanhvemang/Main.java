/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15_part15_baithuchanhvemang;

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
        Scanner in = new Scanner(System.in);
        int[] A;
        System.out.println("Nhap vao so luong phan tu cua mang: ");
        int n = in.nextInt();
        
        A = new int[n];
        for(int i = 0; i < n; i++){
            System.out.println("Nhap phan tu thu " + i);
            A[i] = in.nextInt();
        }
        
        System.out.println("Cac phan tu cua mang da duoc nhap!");
        for(int i = 0; i <n; i++){
            System.out.println(A[i] + "\t");
        }
    }
    
}
