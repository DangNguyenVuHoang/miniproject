package com.example.miniproject;

public class MenuActivity {
    private String Ten;
    private String Mota;
    private int Hinh;
    private String GiaTien;

    public MenuActivity(String ten, String mota, int hinh){
        Ten= ten;
        Mota= mota;
        Hinh= hinh;

    }
    public String getTen(){
        return Ten;

    }
    public void setTen(String ten){
        Ten= ten;

    }
    public String getMota(){
        return Mota;

    }
    public void setMota(String mota){
        Mota= mota;

    }



    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
