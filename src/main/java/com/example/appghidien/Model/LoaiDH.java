package com.example.appghidien.Model;

public class LoaiDH {
    private String MaLoaiDH;
    private String TenLoaiDH;

    public LoaiDH(String maLoaiDH, String tenLoaiDH) {
        MaLoaiDH = maLoaiDH;
        TenLoaiDH = tenLoaiDH;
    }

    public String getMaLoaiDH() {
        return MaLoaiDH;
    }

    public void setMaLoaiDH(String maLoaiDH) {
        MaLoaiDH = maLoaiDH;
    }

    public String getTenLoaiDH() {
        return TenLoaiDH;
    }

    public void setTenLoaiDH(String tenLoaiDH) {
        TenLoaiDH = tenLoaiDH;
    }

    @Override
    public String toString() {
        return TenLoaiDH;
    }
}
