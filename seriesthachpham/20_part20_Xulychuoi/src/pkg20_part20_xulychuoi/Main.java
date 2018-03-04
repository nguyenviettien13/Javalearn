/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20_part20_xulychuoi;

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
        String st = "abc";
        if (st.equals("Abc")){
            System.out.println("hai chuoi abc va Abc bang nhau"
                    + " (so sanh kieu equals)");
        } else {
            System.out.println("Hai chuoi abc va Abc khong bang nhau(so sanh "
                    + "kieu equals)");
        }
        if (st.equalsIgnoreCase("Abc")){
            System.out.println("hai chuoi abc va Abc bang nhau"
                    + " (so sanh kieu equalsIgnoreCase)");
        } else {
            System.out.println("Hai chuoi abc va Abc khong bang nhau(so sanh "
                    + "kieu equalsIgnoreCase)");
        }
        
        System.out.println("uppercase của abc la : "+ st.toUpperCase());
        
        System.out.println("Ky tu thu 2 cua chuoi abc la: " + st.charAt(1));
        
        //ta co mot chuoi 
        String cau = "NguyenkhoangtrangVietkhoangtrangTienkhoangtrangiskhoangtrangthekhoangtrangbest";
        String[] tuarray = cau.split("khoangtrang");
        for(String str: tuarray){
            System.out.println(str);
        }
    }
    
}
