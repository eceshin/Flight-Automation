package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Ucus {

    public void UcusTipi() throws SQLException {
        System.out.println("1)Yurtdışı uçuşları listele");
        System.out.println("2)Yurtiçi uçuşları listele");
        Scanner scanner = new Scanner(System.in);
        UcusTip ucusTip = new UcusTip();

        int tip = scanner.nextInt();
        if (tip == 1) {
            ucusTip.UcusAra(1);
        } else if (tip == 2) {

            ucusTip.UcusAra(2);
        } else
            System.out.println("Hatalı seçim");
    }
}
