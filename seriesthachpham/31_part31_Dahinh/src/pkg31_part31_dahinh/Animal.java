/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg31_part31_dahinh;

/**
 *
 * @author NguyenVietTien
 */
public class Animal implements Climb{
    public void  mytype(){
        System.out.println("I 'm Animal");
    }

    @Override
    public void leotreo() {
        System.out.println("Leo treo");
    }
}
