package com.example.transferee.helpers;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static String getStringDateWithDay(Date date) {
        if (isYesterday(date))
            return "Yesterday";
        if (DateUtils.isToday(date.getTime()))
            return "Today";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String getStringDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static boolean isYesterday(Date date) {
        return DateUtils.isToday(date.getTime() + DateUtils.DAY_IN_MILLIS);
    }
}
