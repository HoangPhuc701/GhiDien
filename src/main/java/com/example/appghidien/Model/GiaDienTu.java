package com.example.appghidien.Model;

public class GiaDienTu {
    private String MaGiaDienTu;
    private String ThangApDung;
    private double CaoDiem;
    private double ThapDiem;
    private double BinhThuong;

    public GiaDienTu(String maGiaDienTu, String thangApDung, double caoDiem, double thapDiem, double binhThuong) {
        MaGiaDienTu = maGiaDienTu;
        ThangApDung = thangApDung;
        CaoDiem = caoDiem;
        ThapDiem = thapDiem;
        BinhThuong = binhThuong;
    }

    public String getMaGiaDienTu() {
        return MaGiaDienTu;
    }

    public void setMaGiaDienTu(String maGiaDienTu) {
        MaGiaDienTu = maGiaDienTu;
    }

    public String getThangApDung() {
        return ThangApDung;
    }

    public void setThangApDung(String thangApDung) {
        ThangApDung = thangApDung;
    }

    public double getCaoDiem() {
        return CaoDiem;
    }

    public void setCaoDiem(double caoDiem) {
        CaoDiem = caoDiem;
    }

    public double getThapDiem() {
        return ThapDiem;
    }

    public void setThapDiem(double thapDiem) {
        ThapDiem = thapDiem;
    }

    public double getBinhThuong() {
        return BinhThuong;
    }

    public void setBinhThuong(double binhThuong) {
        BinhThuong = binhThuong;
    }
}
