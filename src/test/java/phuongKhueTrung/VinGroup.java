package phuongKhueTrung;

import phuongHoaAn.FPTCorporation;

public class VinGroup {

    //Pham vi ngoai class nyung khac package
    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "Vin LCD";
        fpt.setTVName();
    }
}
