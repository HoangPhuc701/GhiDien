package com.example.appghidien.Model;

public class Nha {
    private String MaNha;
    private String SoNha;
    private String TenDuong;
    private String TenChuHo;
    private String MaPhuongXa;
    public Nha() {}

    public Nha(String maNha, String soNha, String tenDuong, String maPhuongXa, String tenChuHo) {
        MaNha = maNha;
        SoNha = soNha;
        TenDuong = tenDuong;
        MaPhuongXa = maPhuongXa;
    }

    public String getMaNha() {
        return MaNha;
    }

    public void setMaNha(String maNha) {
        MaNha = maNha;
    }

    public String getSoNha() {
        return SoNha;
    }

    public void setSoNha(String soNha) {
        SoNha = soNha;
    }

    public String getTenDuong() {
        return TenDuong;
    }

    public void setTenDuong(String tenDuong) {
        TenDuong = tenDuong;
    }

    public String getMaPhuongXa() {
        return MaPhuongXa;
    }

    public void setMaPhuongXa(String maPhuongXa) {
        MaPhuongXa = maPhuongXa;
    }

    public String getTenChuHo() {
        return TenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        TenChuHo = tenChuHo;
    }

    @Override
    public String toString() {
        return "Nha{" +
                "MaNha='" + MaNha + '\'' +
                ", SoNha='" + SoNha + '\'' +
                ", TenDuong='" + TenDuong + '\'' +
                ", MaPhuongXa='" + MaPhuongXa + '\'' +
                '}';
    }
}
