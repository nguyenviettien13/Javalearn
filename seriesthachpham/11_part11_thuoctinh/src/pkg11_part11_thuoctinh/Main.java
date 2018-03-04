/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg11_part11_thuoctinh;

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
        Dog dog = new Dog(4.4, "Vang", "Phu Quoc");
        dog.sua();
        System.out.println("Thong tin cho: " + dog.getgiongcho() + "  "+  dog.getmaulong());
    }
    
}
