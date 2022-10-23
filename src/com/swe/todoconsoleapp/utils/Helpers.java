package com.swe.todoconsoleapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    public static Date covertStringToDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static String covertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            return simpleDateFormat.format(date);


    }
}
