package com.company;

public class BaseHesapla {
    private double yeniUcret;

    public double getYeniUcret() {

        return yeniUcret;
    }

    public void setYeniUcret(double yeniUcret) {
        this.yeniUcret = yeniUcret;
        if (yeniUcret < 0) {
            System.out.println("Hesaplanan ucret 0dan küçük olamaz");
        }

    }

    public double Indirim(double ucret, int yas) {
        if (yas > 5 && yas < 20) {
            yeniUcret = ucret * 0.8;
            System.out.println("indirimsiz fiyat :" + ucret);
            System.out.println("indirimli fiyat :" + yeniUcret);

        } else if (yas > 40 && yas < 70) {
            yeniUcret = ucret * 0.9;
            System.out.println("indirimsiz fiyat :" + ucret);
            System.out.println("indirimli fiyat :" + yeniUcret);
        } else {
            System.out.println("İndiriminiz bulunmamaktadır ücretiniz:" + ucret);
        }
        return ucret;
    }

    public double Iade(double ucret) {
        yeniUcret = ucret / 2;
        System.out.println("ödemeniz yapıldı:" + yeniUcret);

        return ucret;
    }

}

