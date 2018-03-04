/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg39_part39_thuchanhxulysukienchuot;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author NguyenVietTien
 */
public class Rectangle extends Shape{
    private int _width;
    private int _height;
    private int[] _toadotuongdoi;

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setWidth(int _width) {
        this._width = _width;
    }

    public void setHeight(int _height) {
        this._height = _height;
    }
    
    public void setToadotuongdoi(int[] _toadotuongdoi) {
        this._toadotuongdoi = _toadotuongdoi;
    }

    public int[] getToadotuongdoi() {
        return _toadotuongdoi;
    }
    
    
    //kiem tra xem diem co toa do (x, y) co thuoc vao hinh chu nhat khong
    @Override
    public boolean checkinArea(int x, int y) {
        if(     x >= getX()&& 
                x <= getX()+ getWidth()&& 
                y >= getY()&&
                y <= getY()+ getHeight()
          ){
        return true;
        } else {
            return false;
        }
    }
    
    //truyen vao toa do chuot ta se tra ve toa do cần dịch chuyển
    public int[] toadocandichhinh(int x, int y) {
        _toadotuongdoi = new int[2];
        _toadotuongdoi[0] = x - getX();
        _toadotuongdoi[1] = y - getY();
        return _toadotuongdoi;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
