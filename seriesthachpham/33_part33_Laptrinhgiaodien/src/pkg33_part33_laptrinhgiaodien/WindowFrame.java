/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg33_part33_laptrinhgiaodien;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author NguyenVietTien
 */
public class WindowFrame extends JFrame{
    public WindowFrame(){
        super("Title of Frame");
        setSize(400,400);
        
        setLayout(new FlowLayout());
        JLabel lb1 = new JLabel("Nguyen Viet Tien");
        add(lb1);
        JLabel lb2 = new JLabel("Sinh vien nam 4");
        add(lb2);
        JLabel lb3 = new JLabel("Dai hoc HNKB");
        add(lb3);
    }
    
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        wf.setVisible(true);
    }
}
