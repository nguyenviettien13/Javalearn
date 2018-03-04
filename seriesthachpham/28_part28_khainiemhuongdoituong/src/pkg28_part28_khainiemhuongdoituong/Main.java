/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg28_part28_khainiemhuongdoituong;

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
        System.out.println("---------Cách gửi thứ 1---------");
        sendms("Toi yeu em");
        System.out.println("---------Cách gửi thứ 2---------");
        EmailSender es = new EmailSender();
        es.Send("Toi yeu em");
    }
    public static void sendms(String message){
        System.out.println("message "+ message + " is send to Micheal");
    }
    
}
