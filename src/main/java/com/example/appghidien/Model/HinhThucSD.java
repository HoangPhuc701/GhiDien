package com.example.appghidien.Model;

public class HinhThucSD {
    private String MaHinhThuc;
    private String TenHinhThuc;

    public HinhThucSD(String maHinhThuc, String tenHinhThuc) {
        MaHinhThuc = maHinhThuc;
        TenHinhThuc = tenHinhThuc;
    }

    public String getMaHinhThuc() {
        return MaHinhThuc;
    }

    public void setMaHinhThuc(String maHinhThuc) {
        MaHinhThuc = maHinhThuc;
    }

    public String getTenHinhThuc() {
        return TenHinhThuc;
    }

    public void setTenHinhThuc(String tenHinhThuc) {
        TenHinhThuc = tenHinhThuc;
    }

    @Override
    public String toString() {
        return TenHinhThuc;
    }
}
