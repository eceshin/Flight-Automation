package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

public class Odeme {
    Connection connection = null;
    DbHelper dbHelper = new DbHelper();
    Statement statement = null;
    ResultSet resultSet;
    boolean ucus = true;

    double ucrettutucu = 0;
    int yastutucu = 0;
    ;

    public void Odemecik(String kalkis, String varis, String saat, int yas) throws SQLException {

        connection = dbHelper.getConnection();
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM ucus.ucak where kalkis_yon='" + kalkis + "' and varis_yon='" + varis + "' and saat='" + saat + "'");
        if (resultSet.next()) {
            while (resultSet.next()) {
                ucrettutucu = resultSet.getDouble("ucret");
            }
        } else {
            System.out.println("YANLIŞ KALKIŞ YÖNÜ GİRDİNİZ");
            ucus = false;
        }
        if (ucus==true) {
            BaseHesapla baseHesapla = new BaseHesapla();

            resultSet = statement.executeQuery("SELECT * FROM ucus.kullanici where kalkisYonu='" + kalkis + "' and varisYonu='" + varis + "' and saat='" + saat + "'");
            if (resultSet.next()) {
                while (resultSet.next()) {
                    yastutucu = resultSet.getInt("kullaniciYasi");
                }
                baseHesapla.Indirim(ucrettutucu, yastutucu);
            } else System.out.println("YANLIŞ KALKIŞ YÖNÜ GİRDİNİZ");

        }
    }

    public void Iade(String ucusNo) throws SQLException {

        System.out.println("Yaptığınız ödemenin yalnızca %50'si iade edilir. Devam etmek istiyor musunuz?");
        Scanner scanner = new Scanner(System.in);
        String cevap = scanner.next();
        switch (cevap.toLowerCase(Locale.ROOT)) {
            case "evet":
                connection = dbHelper.getConnection();
                statement = connection.createStatement();

                resultSet = statement.executeQuery("SELECT * FROM ucus.ucak where ucus_no='" + ucusNo + "'");
                if (resultSet.next()) {
                    while (resultSet.next()) {
                        ucrettutucu = resultSet.getDouble("ucret");
                    }
                    BaseHesapla baseHesapla = new BaseHesapla();
                    baseHesapla.Iade(ucrettutucu);
                } else System.out.println("Yanlış uçuş numarası girdiniz");
                break;

            case "hayır":
                System.out.println("İŞLEMİNİZ SONLANDIRILDI");
                break;
        }
    }
}
