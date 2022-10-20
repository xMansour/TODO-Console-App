package com.swe.todoconsoleapp.utils;

public class InputValidator {
    public static boolean isValidNumber(String input, int start, int end) {
        try {
            var number = Integer.parseInt(input);
            if (number >= start && number <= end)
                return true;
            else
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
