/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg10_part10_method;

/**
 *
 * @author NguyenVietTien
 */
public class Dog {
    private double _cannang;
    public Dog(){
        
    }
    public void setcannang(double cannang){
        _cannang = cannang;
    }
    public double getcannang(){
        return _cannang;
    }
    public void sua(){
        System.out.println("Gau gau");
    }
    public void cannguoi(int sophat){
        System.out.println("Can nguoi " + sophat + " phat ");
    }
    
}

