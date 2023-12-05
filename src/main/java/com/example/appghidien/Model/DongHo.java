package com.example.appghidien.Model;

public class DongHo {
    private String MaDongHoDien;
    private String MaHinhThuc;
    private String MaCapDienAp;
    private String MaLoaiDH;

    public DongHo() {
    }

    public DongHo(String maDongHoDien, String maHinhThuc, String maCapDienAp, String maLoaiDH) {
        MaDongHoDien = maDongHoDien;
        MaHinhThuc = maHinhThuc;
        MaCapDienAp = maCapDienAp;
        MaLoaiDH = maLoaiDH;
    }

    public String getMaDongHoDien() {
        return MaDongHoDien;
    }

    public void setMaDongHoDien(String maDongHoDien) {
        MaDongHoDien = maDongHoDien;
    }

    public String getMaHinhThuc() {
        return MaHinhThuc;
    }

    public void setMaHinhThuc(String maHinhThuc) {
        MaHinhThuc = maHinhThuc;
    }

    public String getMaCapDienAp() {
        return MaCapDienAp;
    }

    public void setMaCapDienAp(String maCapDienAp) {
        MaCapDienAp = maCapDienAp;
    }

    public String getMaLoaiDH() {
        return MaLoaiDH;
    }

    public void setMaLoaiDH(String maLoaiDH) {
        MaLoaiDH = maLoaiDH;
    }

    @Override
    public String toString() {
        return "DongHo{" +
                "MaDongHoDien='" + MaDongHoDien + '\'' +
                ", MaHinhThuc='" + MaHinhThuc + '\'' +
                ", MaCapDienAp='" + MaCapDienAp + '\'' +
                ", MaLoaiDH='" + MaLoaiDH + '\'' +
                '}';
    }
}
