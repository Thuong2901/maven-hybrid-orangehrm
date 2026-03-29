package phuongHoaAn;

public class TPBank {

    //Pham vi ngoai Class nhung trong 1 package
    public void showTVName(){
        FPTCorporation fpt = new FPTCorporation();
        fpt.tvName = "TPBanl LCD";
        fpt.setTvColor();
        fpt.tvColor ="";
        fpt.tvChannel="";
        fpt.setTvChannel();
    }
}
