package com.example.transferee.helpers;

public class StringHelper {
    public static String getDashIfNumberIsZero(Integer number) {
        if (number == null)
            return "-";
        if (number == 0)
            return "-";
        return Integer.toString(number);
    }

    public static String getNumberWithApostropheIfValueIsNotZero(Integer number) {
        if (number == null)
            return "-";
        if (number == 0)
            return "-";
        return Integer.toString(number) + "'";
    }

    public static String getOrdinalFromInteger(Integer number) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (number % 100) {
            case 11:
            case 12:
            case 13:
                return number + "th";
            default:
                return number + sufixes[number % 10];

        }
    }

    public static String getDashIfStringIsNull(String string) {
        if (string == null)
            return "-";
        return string;
    }
}
