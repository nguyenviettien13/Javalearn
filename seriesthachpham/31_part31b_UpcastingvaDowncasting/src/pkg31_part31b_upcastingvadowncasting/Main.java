/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg31_part31b_upcastingvadowncasting;

/**
 *
 * @author NguyenVietTien
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Upcasting
        Cha cha = new ConCai();
        cha.method1();
        
        //Downcasting
        if(cha instanceof ConCai){
            ConCai concai = (ConCai) cha;
            concai.method1();
            concai.method2();
        }
        //Chưng minh rằng chỉ tạo được đối tượng mới khi dùng từ khóa new
        //trong vi dụ dưới đây c1, c2 cùng tham chiếu đến 1 đối tượng
        ConCai c1 = new ConCai();
        ConCai c2 = c1;
        c1.setten("tien");
        System.out.println(c2.getten());
    }
}
