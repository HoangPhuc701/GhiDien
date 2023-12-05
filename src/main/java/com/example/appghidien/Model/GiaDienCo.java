package com.example.appghidien.Model;

public class GiaDienCo {
    private String MaGiaCo;
    private String ThangApDung;
    private double Bac1;
    private double Bac2;
    private double Bac3;
    private double Bac4;
    private double Bac5;
    private double Bac6;

    public GiaDienCo(String maGiaCo, String thangApDung, double bac1, double bac2, double bac3, double bac4, double bac5, double bac6) {
        MaGiaCo = maGiaCo;
        ThangApDung = thangApDung;
        Bac1 = bac1;
        Bac2 = bac2;
        Bac3 = bac3;
        Bac4 = bac4;
        Bac5 = bac5;
        Bac6 = bac6;
    }

    public String getMaGiaCo() {
        return MaGiaCo;
    }

    public void setMaGiaCo(String maGiaCo) {
        MaGiaCo = maGiaCo;
    }

    public String getThangApDung() {
        return ThangApDung;
    }

    public void setThangApDung(String thangApDung) {
        ThangApDung = thangApDung;
    }

    public double getBac1() {
        return Bac1;
    }

    public void setBac1(double bac1) {
        Bac1 = bac1;
    }

    public double getBac2() {
        return Bac2;
    }

    public void setBac2(double bac2) {
        Bac2 = bac2;
    }

    public double getBac3() {
        return Bac3;
    }

    public void setBac3(double bac3) {
        Bac3 = bac3;
    }

    public double getBac4() {
        return Bac4;
    }

    public void setBac4(double bac4) {
        Bac4 = bac4;
    }

    public double getBac5() {
        return Bac5;
    }

    public void setBac5(double bac5) {
        Bac5 = bac5;
    }

    public double getBac6() {
        return Bac6;
    }

    public void setBac6(double bac6) {
        Bac6 = bac6;
    }

    @Override
    public String toString() {
        return "GiaDienCo{" +
                "MaGiaCo='" + MaGiaCo + '\'' +
                ", ThangApDung='" + ThangApDung + '\'' +
                ", Bac1='" + Bac1 + '\'' +
                ", Bac2='" + Bac2 + '\'' +
                ", Bac3='" + Bac3 + '\'' +
                ", Bac4='" + Bac4 + '\'' +
                ", Bac5='" + Bac5 + '\'' +
                ", Bac6='" + Bac6 + '\'' +
                '}';
    }
}
