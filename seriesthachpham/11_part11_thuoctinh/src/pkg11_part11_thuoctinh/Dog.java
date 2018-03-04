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
public class Dog {
    private double _tuoi;
    private String _maulong;
    private String _giongcho;
    
    public Dog(){
        this._tuoi = 0;
        _maulong = "";
        _giongcho = "";
    }
    public Dog(double tuoi, String maulong, String giongcho){
        this._tuoi = tuoi;
        _maulong = maulong;
        _giongcho = giongcho;
    }
    
    public void settuoi(double tuoi){
        _tuoi = tuoi;
    }
    public double gettuoi(){
        return _tuoi;
    }
    
    public void setmaulong(String maulong){
        _maulong = maulong;
    }
    public String getmaulong(){
        return _maulong;
    }
    
    public void setgiongcho(String giongcho){
        _giongcho = giongcho;
    }
    public String getgiongcho(){
        return _giongcho;
    }
    public void sua(){
        System.out.println("go go! ahu");
    }
}
