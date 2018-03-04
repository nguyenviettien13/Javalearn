/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg30_part30_dakethuainterface;

/**
 *
 * @author NguyenVietTien
 */
public class ConCai implements Cha, Me{

    @Override
    public void KiemTien() {
        System.out.println("Kiem tien: Cai nay ke thua cua Cha");
    }

    @Override
    public void DayDoConCai() {
        System.out.println("Day do con cai: Cai nay ke thua cua Cha");
    }

    @Override
    public void NauAn() {
        System.out.println("Nau ăn: Cai này kế thừa của mẹ");
    }

    @Override
    public void ChamSocGiaDinh() {
        System.out.println("Cham sóc gia đình: Cái này kế thừa của mẹ");
    }
}
