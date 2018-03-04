/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg37_part37_dohoacoban;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author NguyenVietTien
 */
public class WindowFrame extends JFrame{
    public WindowFrame(){
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //override method paint của Jframe
    public void paint(Graphics g){
        //super.paint(g);
        g.setColor(Color.red);
        g.drawLine(0, 0, 100, 100);
        
        g.setColor(Color.yellow);
        g.drawRect(50, 50, 100, 100);
        
        g.setColor(Color.orange);
        g.fillRect(55, 55, 90, 90);
        
    }
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
    }
}
