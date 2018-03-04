/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg02_part2_khaibaovasudungbien;

/**
 *
 * @author NguyenVietTien
 */
public class Variable {
    public String phamvi = "Đây là biến public được sử dụng ở tất cả mọi "
            + "nơi";
    
    public static void main(String[] args) {
        int tuoi;
        tuoi = 22;
        float cannang;
        cannang = 65.1f;
        System.out.println("Tuoi cua Tien la: "+ tuoi);
        System.out.println("Can nặng cua Tien la: "+ cannang);
        System.out.println("Khoi tao doi tuong doituong");
        Variable doituong = new Variable();
        System.out.println(doituong.phamvi);
    }   
}

// tại sao nhấn build nó lại không chạy