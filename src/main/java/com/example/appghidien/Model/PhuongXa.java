package com.example.appghidien.Model;

public class PhuongXa {
    private String MaPhuongXa;
    private String TenPhuongXa;
    private String MaQuanHuyen;

    public PhuongXa() {}

    public PhuongXa(String maPhuongXa, String tenPhuongXa, String maQuanHuyen) {
        MaPhuongXa = maPhuongXa;
        TenPhuongXa = tenPhuongXa;
        MaQuanHuyen = maQuanHuyen;
    }

    public String getMaPhuongXa() {
        return MaPhuongXa;
    }

    public void setMaPhuongXa(String maPhuongXa) {
        MaPhuongXa = maPhuongXa;
    }

    public String getTenPhuongXa() {
        return TenPhuongXa;
    }

    public void setTenPhuongXa(String tenPhuongXa) {
        TenPhuongXa = tenPhuongXa;
    }

    public String getMaQuanHuyen() {
        return MaQuanHuyen;
    }

    public void setMaQuanHuyen(String maQuanHuyen) {
        MaQuanHuyen = maQuanHuyen;
    }

    @Override
    public String toString() {
        return TenPhuongXa;
    }
}
