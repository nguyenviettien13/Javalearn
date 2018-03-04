/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg25_part25_tukhoastatic;

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
        Dog dog = new Dog("Nguyen");
        Dog dog1 = new Dog("Sandro");
        Dog dog2 = new Dog("Hundred");
        System.out.println(Dog.socho);
        
        
    }
}

class Dog{
    public static int socho = 0;
    private String _ten;
    
    static {
        System.out.println("Khoi lenh static chi duoc goi 1 lan duy nhat");
    }
    Dog(String ten){
        socho++;
        _ten = ten;
        System.out.println("Constructor duoc goi");
    }
}