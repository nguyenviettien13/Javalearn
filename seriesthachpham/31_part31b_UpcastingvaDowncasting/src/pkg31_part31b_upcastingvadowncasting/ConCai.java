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
public class ConCai extends Cha{
    
    private String _ten;
    
    public void setten(String ten){
        _ten = ten;
    }
    
    public String getten(){
        return _ten;
    }
    
    public void method1(){
        System.out.println("ConCai: Method 1");
    }
    public void method2(){
        System.out.println("ConCai: Method 2");
    }
}
