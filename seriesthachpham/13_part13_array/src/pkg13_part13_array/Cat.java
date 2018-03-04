/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg13_part13_array;

/**
 *
 * @author NguyenVietTien
 */
public class Cat {
    private int _tuoi;
    private String _ten;
    public Cat(){
        _tuoi = 0;
        _ten = "chua duoc dat ten";
    }
    public Cat(int tuoi, String ten){
        _tuoi = tuoi;
        _ten = ten;
    }
    public void settuoi(int tuoi){
        _tuoi = tuoi;
    }
    public int gettuoi(){
        return _tuoi;
    }
    public void setten(String ten){
        _ten = ten;
    }
    public String getten(){
        return _ten;
    }
}