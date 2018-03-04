/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg38_part38_mouselistener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

/**
 *
 * @author NguyenVietTien
 */
public class WindowFrame extends JFrame implements MouseListener, MouseMotionListener{
    private int x,y;
    public WindowFrame(){
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(0, 0, getWidth(),getHeight());
        
        g.setColor(Color.red);
        g.fillRect(x, y, 100, 100);
    }
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Su kien mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Su kien mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("su kien mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Enter Frame");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exit Frame");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            System.out.println("Goi su kien MouseDragged");
            x= e.getX();
            y= e.getY();
            repaint();
            
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Goi su kien mouseMoved");
    }
}
