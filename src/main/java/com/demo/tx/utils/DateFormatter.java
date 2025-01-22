package com.demo.tx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String formatDateAsString(Date now, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        // Format the date as required
        String formattedDate = formatter.format(now);
        return formattedDate;
    }
}
