package com.example.appghidien.Model;

public class QuanHuyen {
    private String MaQuanHuyen;
    private String TenQuanHuyen;
    private String MaTinhTP;

    public QuanHuyen(String maQuanHuyen, String tenQuanHuyen, String maTinhTP) {
        MaQuanHuyen = maQuanHuyen;
        TenQuanHuyen = tenQuanHuyen;
        MaTinhTP = maTinhTP;
    }

    public QuanHuyen() {
    }

    public String getMaQuanHuyen() {
        return MaQuanHuyen;
    }

    public void setMaQuanHuyen(String maQuanHuyen) {
        MaQuanHuyen = maQuanHuyen;
    }

    public String getTenQuanHuyen() {
        return TenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        TenQuanHuyen = tenQuanHuyen;
    }

    public String getMaTinhTP() {
        return MaTinhTP;
    }

    public void setMaTinhTP(String maTinhTP) {
        MaTinhTP = maTinhTP;
    }

    @Override
    public String toString() {
        return TenQuanHuyen;

    }
}
