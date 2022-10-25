package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.NoDateAssignedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    public static Date covertStringToDate(String date) throws InvalidDateFormatException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidDateFormatException("date format must follow dd/MM/yyy");
        }
    }

    public static String covertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.format(date);
        } catch (NullPointerException ex) {
            throw new NoDateAssignedException("No date assigned");
        }
    }
}
