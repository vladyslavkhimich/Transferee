package com.example.transferee.helpers;

import java.text.DecimalFormat;

public class DoubleHelper {
    public static String formatDouble(double value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(value);
    }
}
