package com.example.appghidien.Model;

public class NhanVien {
    private String MaNV;
    private String TenNV;
    private String NgaySinh;
    private String ChucVu;
    private String MatKhau;
    private String MaPhuongXa;

    public NhanVien(String maNV, String tenNV, String ngaySinh, String chucVu, String matKhau, String maPhuongXa) {
        MaNV = maNV;
        TenNV = tenNV;
        NgaySinh = ngaySinh;
        ChucVu = chucVu;
        MatKhau = matKhau;
        MaPhuongXa = maPhuongXa;
    }

    public NhanVien() {}

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getMaPhuongXa() {
        return MaPhuongXa;
    }

    public void setMaPhuongXa(String maPhuongXa) {
        MaPhuongXa = maPhuongXa;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "MaNV='" + MaNV + '\'' +
                ", TenNV='" + TenNV + '\'' +
                ", NgaySinh='" + NgaySinh + '\'' +
                ", ChucVu='" + ChucVu + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", MaPhuongXa='" + MaPhuongXa + '\'' +
                '}';
    }
}
