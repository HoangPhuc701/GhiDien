package com.example.appghidien.Model;

public class Tinh {
    private String MaTinhTP;
    private String TenTinhTP;

    public Tinh() {}

    public Tinh(String maTinhTP, String tenTinhTP) {
        MaTinhTP = maTinhTP;
        TenTinhTP = tenTinhTP;
    }

    public String getMaTinhTP() {
        return MaTinhTP;
    }

    public void setMaTinhTP(String maTinhTP) {
        MaTinhTP = maTinhTP;
    }

    public String getTenTinhTP() {
        return TenTinhTP;
    }

    public void setTenTinhTP(String tenTinhTP) {
        TenTinhTP = tenTinhTP;
    }

    @Override
    public String toString() {
        return TenTinhTP;
    }
}
