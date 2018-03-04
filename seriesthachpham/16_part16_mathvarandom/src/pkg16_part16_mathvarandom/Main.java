/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg16_part16_mathvarandom;

import java.util.Random;

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
        Random rand = new Random();
        System.out.println("Gia tri so so ngay hom nay la: " + rand.nextInt(10));
    }
    
}
