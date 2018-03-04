/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg39_part39_thuchanhxulysukienchuot;

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
    private Rectangle rect;
    public WindowFrame(){
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        rect = new Rectangle();
        rect.setX(100);
        rect.setY(100);
        rect.setWidth(100);
        rect.setHeight(100);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
        if(rect.checkinArea(e.getX(), e.getY())){
            System.out.println("Checkin");
            rect.toadocandichhinh(e.getX(), e.getY());
            rect.setIspressonshape(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        rect.setIspressonshape(false);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(rect.getIspressonshape()== true){
            rect.setX(e.getX()- rect.getToadotuongdoi()[0]);
            rect.setY(e.getY()- rect.getToadotuongdoi()[1]);
        } else {
            return;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        rect.drawShape(g);
    }
    
    public static void main(String[] args) {
        new WindowFrame();
    }
}
