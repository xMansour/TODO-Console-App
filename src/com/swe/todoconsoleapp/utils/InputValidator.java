package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.Priority;

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

    public static boolean isValidPriority(String input) {
        if (input.toUpperCase().equals(Priority.HIGH.name()) || input.toUpperCase().equals(Priority.MEDIUM.name()) || input.toUpperCase().equals(Priority.LOW.name()))
            return true;
        return false;
    }
}
