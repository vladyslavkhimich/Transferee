package com.example.transferee.helpers;

public class StringHelper {
    public static String getDashIfNumberIsZero(int number) {
        if (number == 0)
            return "-";
        return Integer.toString(number);
    }

    public static String getNumberWithApostropheIfValueIsNotZero(int number) {
        if (number == 0)
            return "-";
        return Integer.toString(number) + "'";
    }
}
