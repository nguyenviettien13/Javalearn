/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19_part19_datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getHours());
        
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/YYYY");
        System.out.println(sdf.format(date));
    }
    
}
