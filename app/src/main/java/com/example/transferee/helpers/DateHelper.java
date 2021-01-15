package com.example.transferee.helpers;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static String getStringDateWithDay(String stringDate) throws ParseException {
        if (stringDate != null) {
            Date date = getDateFromJSONString(stringDate);
            if (isYesterday(date))
                return "Yesterday";
            if (DateUtils.isToday(date.getTime()))
                return "Today";
            SimpleDateFormat dateFormatFinal = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
            return dateFormatFinal.format(date);
        }
        return "No date";
    }

    public static String getStringDate(String stringDate) throws ParseException {
        if (stringDate != null) {
            Date date = getDateFromJSONString(stringDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
            return dateFormat.format(date);
        }
        return "No date";
    }

    public static boolean isYesterday(Date date) {
        return DateUtils.isToday(date.getTime() + DateUtils.DAY_IN_MILLIS);
    }

    public static Date getDateFromJSONString(String stringDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(stringDate);
    }
}
