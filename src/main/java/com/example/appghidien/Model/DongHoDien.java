package com.example.appghidien.Model;

public class DongHoDien {
    private String MaDongHoDien;
    private String MaHinhThuc;
    private String MaCapDienAp;
    private String MaNha;
    private String MaLoai;
    private String DiaChi;

    public DongHoDien(String maDongHoDien, String maHinhThuc, String maCapDienAp, String maNha, String maLoai, String diaChi) {
        MaDongHoDien = maDongHoDien;
        MaHinhThuc = maHinhThuc;
        MaCapDienAp = maCapDienAp;
        MaNha = maNha;
        MaLoai = maLoai;
        DiaChi = diaChi;
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

    public String getMaNha() {
        return MaNha;
    }

    public void setMaNha(String maNha) {
        MaNha = maNha;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @Override
    public String toString() {
        return  MaDongHoDien;
    }
}
