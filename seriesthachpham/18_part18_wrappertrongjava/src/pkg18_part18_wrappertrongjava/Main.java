/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg18_part18_wrappertrongjava;

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
        
        int i = 5;
        float f = 2.3f;
        boolean b = true;
        double d = 3.22;
        
        Integer Ite = new Integer("123");
        System.out.println(""+(Ite.intValue()+4));
        
        Float F = new Float(3.4);
        
        //Convert String to Int
        String number = "123";
        int inumber = Integer.valueOf(number);
        System.out.println(""+(inumber +10));
        
    }
    
}
