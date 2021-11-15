package com.coffemachine.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT_FOR_FILENAME = "yyyy-mm-dd-hh-mm-ss";

    public static String getDateAsFileName() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_FILENAME);

        return dateFormat.format(date);
    }
}
