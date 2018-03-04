/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg12_part_constructor;

/**
 *
 * @author NguyenVietTien
 */
public class Dog {
    private int _sochan;
    private int _soduoi;
    private String _ten;
    
    public void setsochan(int sochan){
        _sochan = sochan;
    }
    public int getsochan(){
        return _sochan;
    }
    public void setsoduoi(int soduoi){
        _soduoi = soduoi;
    }
    public int getsoduoi(){
        return _soduoi;
    }
        
    public void setten(String ten){
        _ten = ten;
    }
    public String getten(){
        return _ten;
    }
    
    public Dog(){
        
    }
    
    public Dog(int sochan, int soduoi, String ten){
        _sochan = sochan;
        _soduoi = soduoi;
        _ten = ten;
    }
    
}
