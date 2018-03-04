/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg34_part34_maytinhbotui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author NguyenVietTien
 */
public class WindowFrame extends JFrame implements ActionListener{
    private final JTextField output;
    private final JButton addbutton;
    private final JButton subbutton;
    private final JButton resultbutton;
    private JButton [] numbers;
    
    public WindowFrame(){
        super("Tien'sCalculator");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel topPanel= new JPanel();
        //topPanel.setBackground(Color.RED);
        add(topPanel,BorderLayout.NORTH);
        
        JPanel centerPanel= new JPanel();
        //centerPanel.setBackground(Color.RED);
        add(centerPanel,BorderLayout.CENTER);
        
        output = new JTextField(20);
        topPanel.add(output);
        
        addbutton = new JButton("+");
        addbutton.addActionListener(this);
        centerPanel.add(addbutton);
        
        subbutton = new JButton("-");
        subbutton.addActionListener(this);
        centerPanel.add(subbutton);
        
        resultbutton = new JButton("=");
        resultbutton.addActionListener(this);
        centerPanel.add(resultbutton);
        
        numbers = new JButton[10];
        for(int i = 0; i < 10; i++){
            numbers[i] = new JButton(""+i);
            numbers[i].addActionListener(this);
            centerPanel.add(numbers[i]);
        }
    }
    
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        wf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== addbutton){
            System.out.println("click phim +");
        } else if(e.getSource()== subbutton){
            System.out.println("click phim -");
        }else if(e.getSource()== resultbutton){
            System.out.println("click phim =");
        } else {
            for(int i =0; i<10; i++){
                if(e.getSource() == numbers[i]){
                    System.out.println("Phim "+ i);
                    break;
                }
            }
        }
    }
}
