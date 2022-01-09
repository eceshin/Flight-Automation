package com.company;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Rezervasyon {
    Scanner scan = new Scanner(System.in);
    PreparedStatement statement1 = null;
    Connection connection = null;
    DbHelper dbHelper = new DbHelper();
    Statement statement = null;
    boolean ucus = true;

    ResultSet resultSet;
    int rezervasyonNo;
    String Ad;
    String SoyAd;
    private String tcNo;
    private int yas;
    String saat;

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        if (yas < 0) {
            System.out.println("yaşınız negatif olamaz");
            yas = 0;
            ucus = false;
        }
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        if (tcNo.length() == 11) {
            this.tcNo = tcNo;
        } else {
            System.out.println("Tcniz 11 haneli olmak zorunda");
            ucus = false;
        }
    }

    public void UcusSecme() throws SQLException {

        System.out.println("Kalkış yönünüzü giriniz");
        String kalkis = scan.next();
        System.out.println("Varış yönünüzü giriniz");
        String varis = scan.next();
        connection = dbHelper.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM ucus.ucak where kalkis_yon='" + kalkis + "' and varis_yon='" + varis + "'");

        if (resultSet.next()) {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("ucus_no"));
                System.out.print("  ");
                System.out.print(resultSet.getString("kalkis_yon"));
                System.out.print("  ");
                System.out.print(resultSet.getString("varis_yon"));
                System.out.print("  ");
                System.out.print(resultSet.getString("saat"));
                System.out.print("  ");
                System.out.println(resultSet.getDouble("ucret"));

            }
        } else {
            System.out.println("BU YÖNLERDE UÇUŞUMUZ BULUNMAMAKTADIR ");
            ucus = false;
        }
        if (ucus == true) {
            System.out.println("Bir saat dilimi seçiniz:");
            saat = scan.next();

            connection = dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ucus.ucak where saat='" + saat + "'");
            if (resultSet.next()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("saat"));
                }
            } else {
                System.out.println("YANLIŞ SAAT GİRDİNİZ");
                ucus = false;
            }
        }
        connection.close();
        if (ucus == true) {
            Random r = new Random();

            int a = r.nextInt(100);
            int b = r.nextInt(100);
            int c = r.nextInt(100);

            rezervasyonNo = a * b * c * 43;
            System.out.println("Adiniz:");
            Ad = scan.next();
            System.out.println("Soyadiniz:");
            SoyAd = scan.next();
            String isim = Ad + "" + SoyAd;
            System.out.println("TC kimlik no'nuz:");
            tcNo = scan.next();
            setTcNo(tcNo);
            if (ucus == true) {
                System.out.println("Yaşınız:");
                yas = scan.nextInt();
                setYas(yas);
            }

                if (ucus == true) {

                    connection = dbHelper.getConnection();

                    String sql = "insert into ucus.kullanici values(?,?,?,?,?,?,?);";
                    statement1 = connection.prepareStatement(sql);
                    statement1.setString(1, isim);
                    statement1.setInt(2, yas);
                    statement1.setString(3, tcNo);
                    statement1.setInt(4, rezervasyonNo);
                    statement1.setString(5, kalkis);
                    statement1.setString(6, varis);
                    statement1.setString(7, saat);
                    int result = statement1.executeUpdate();

            }
        }

        connection.close();

        if (ucus == true) {
            System.out.println("Ödeme kısmına geçmek için 1 e basın");
            int secenek = scan.nextInt();
            if (secenek == 1) {
                Odeme odeme = new Odeme();
                odeme.Odemecik(kalkis, varis, saat, yas);
                connection = dbHelper.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM ucus.kullanici where kullaniciTc='" + tcNo + "'");
                if (resultSet.next()) {
                    while (resultSet.next()) {
                        System.out.print("rezervasyon numaranız: ");
                        System.out.print(resultSet.getString("rezervasyonNo"));
                    }
                } else System.out.println("TC nizi yanlış girdiniz");
                ucus = false;
            } else
                System.out.println("işleminiz sona erdi");
        }
    }
}
