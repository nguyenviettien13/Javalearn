/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg31_part31_dahinh;

/**
 *
 * @author NguyenVietTien
 */
public class main2 {
      
  
  public static void main(String args[]){  
    Bike b = new Splender();//day la upcasting  
    b.run();  
  }  
}
class Bike{  
  void run(){System.out.println("dang chay");}  
}  
class Splender extends Bike{  
  @Override
  void run(){System.out.println("chay an toan voi 60km");}
  
}
