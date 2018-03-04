/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg36_part36_xulysukienbanphim;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *
 * @author NguyenVietTien
 */
public class WindowFrame extends JFrame implements KeyListener{
    private final int[] keys = {
        65, 66
    };
    private final Stack<Integer> keyinput = new Stack<>();
    public WindowFrame(){
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("phim vua bam"+e.getKeyCode());
        if(keyinput.contains(e.getKeyCode())){
            //do nothing
        } else {
            keyinput.push(e.getKeyCode());
        }
        System.out.println("Stack: "+keyinput.toString());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //kiem tra xem co trung voi to hop phim khong
        if(isKey(keyinput, keys)){
            System.out.println("Ban vua bam to hop phim AB");
        }
        keyinput.clear();
    }
    
    private boolean isKey(Stack<Integer> st, int[] keys){
        System.out.println("Chay ham kiem tra");
        System.out.println(st.toString());
        for(int i = keys.length-1; i >=0 ; i--){
            System.out.println("vong "+i);
            if(st.empty()){
                System.out.println("return false 3");
                return false;
            }else {
                if(keys[i] != st.pop()){
                    System.out.println("return false 2");
                    return false;
                }
            }
        }
        if(!st.empty()){
            System.out.println("return false 1");
            return false;
        } else {
            System.out.println("return true");
            return true;
        }
    }
    
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        wf.setVisible(true);
    }
}
