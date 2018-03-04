/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg21_part21_datientrinh;

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
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    System.out.println("loop1: " + i);
                }
            }
        });
       
        
        Thread thread1 = new Thread( new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    System.out.println("loop2: " + i);
                }
            }
        });
        //thread.start();
        //thread1.start();
        
        System.out.println("-------------Bat dau cach 2-------------"); 
        Mythread mythread1 = new Mythread("mythread1");
        Mythread mythread2 = new Mythread("mythread2");
        mythread1.start();
        mythread2.start();
        
        System.out.println("-------------Bat dau cach 3-------------"); 
        YourThread yourthread1 = new YourThread("yourthread1");
        YourThread yourthread2 = new YourThread("yourthread2");
        yourthread1.start();
        yourthread2.start();
    }
}

class Mythread extends Thread{
    
    public Mythread(String name){
        super(name);
    }
    @Override
    public void run(){
        for (int i = 0; i < 5; i++){
            System.out.println(getName()+"   "+ i);
        }
    }
}

class YourThread implements Runnable{
    
    private String _name;
    private Thread thread;
    public YourThread(String name){
        this._name = name;
        thread = new Thread(this);
    }
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("");
        }
    }
    public void start(){
        thread.start();
    }
}