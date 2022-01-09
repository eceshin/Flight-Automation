package com.company;

import com.company.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Iptal {
    Connection connection = null;
    DbHelper dbHelper = new DbHelper();
    PreparedStatement statement = null;
    ResultSet resultSet;
    int rezNo;
    String sql;
    String ucusNo;
    boolean ucus=true;

    public void Silme() throws SQLException {
        System.out.println("Rezervasyon numaranızı girin:");
        Scanner scan = new Scanner(System.in);
        rezNo=scan.nextInt();
        if(resultSet!=null){
            connection= dbHelper.getConnection();
            sql="SET SQL_SAFE_UPDATES = 0; delete from ucus.kullanici where rezervasyonNo='"+rezNo+"'";
            statement= connection.prepareStatement(sql);

        } else System.out.println("YANLIŞ REZERVASYON NUMARASI GİRDİNİZ");

        if(ucus){
        System.out.println("uçuş numaranızı giriniz");
        ucusNo= scan.next();
        Odeme odeme= new Odeme();
        odeme.Iade(ucusNo);
    }}
}
