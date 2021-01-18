package com.example.transferee.helpers;

import java.text.DecimalFormat;

public class DoubleHelper {
    public static String formatDouble(double value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(value);
    }

    public static String getDashIfNumberIsNullOrZero(Double number) {
        if (number == null)
            return "-";
        if (number == 0)
            return "-";
        return number.toString();
    }

}
