/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg24_part24_docghifile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenVietTien
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            // TODO code application logic here

            FileReader fr = new FileReader("input.txt");                                                                                                                
            BufferedReader br = new                                                                                                                                                                                                                                                                                                             BufferedReader(fr);
            
            FileWriter fw = new FileWriter("output.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            String line;
            while((line = br.readLine()) != null){
                pw.println(line);
            }
            fr.close();
            fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
