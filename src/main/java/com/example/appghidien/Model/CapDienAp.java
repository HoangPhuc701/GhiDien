package com.example.appghidien.Model;

public class CapDienAp {
    private String MaCapDienAp;
    private String TenCapDienAp;

    public CapDienAp(String maCapDienAp, String tenCapDienAp) {
        MaCapDienAp = maCapDienAp;
        TenCapDienAp = tenCapDienAp;
    }

    public String getMaCapDienAp() {
        return MaCapDienAp;
    }

    public void setMaCapDienAp(String maCapDienAp) {
        MaCapDienAp = maCapDienAp;
    }

    public String getTenCapDienAp() {
        return TenCapDienAp;
    }

    public void setTenCapDienAp(String tenCapDienAp) {
        TenCapDienAp = tenCapDienAp;
    }

    @Override
    public String toString() {
        return TenCapDienAp;
    }
}
