package com.example.appghidien.Model;

public class KhachHang {
    private String MaKH;
    private String TenKH;
    private String Sdt;
    private String MatKhau;
    private String MaNha;

    public KhachHang(String maKH, String tenKH, String sdt, String matKhau, String maNha) {
        MaKH = maKH;
        TenKH = tenKH;
        Sdt = sdt;
        MatKhau = matKhau;
        MaNha = maNha;
    }

    public KhachHang() {
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getMaNha() {
        return MaNha;
    }

    public void setMaNha(String maNha) {
        MaNha = maNha;
    }
}
