/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg39_part39_thuchanhxulysukienchuot;

import java.awt.Graphics;

/**
 *
 * @author NguyenVietTien
 */
public abstract class Shape {
    private int _x;
    private int _y;
    private boolean _ispressonshape = false;

    public void setIspressonshape(boolean _ispressonshape) {
        this._ispressonshape = _ispressonshape;
    }

    public boolean getIspressonshape() {
        return _ispressonshape;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int _x) {
        this._x = _x;
    }

    public void setY(int _y) {
        this._y = _y;
    }
    
    public abstract boolean checkinArea(int x, int y);
    
    public abstract int[] toadocandichhinh(int x, int y);
    
    public abstract void drawShape(Graphics g);
}
