package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.Priority;

public class InputValidator {
    public static boolean isValidNumber(String input, int start, int end) {
        try {
            var number = Integer.parseInt(input);
            return number >= start && number <= end;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPriority(String input) {
        return input.toUpperCase().equals(Priority.HIGH.name()) || input.toUpperCase().equals(Priority.MEDIUM.name()) || input.toUpperCase().equals(Priority.LOW.name());
    }
    public static boolean isValidCategory(String input) {
        return input.toUpperCase().equals(Category.HOBBY.name()) || input.toUpperCase().equals(Category.WORK.name()) || input.toUpperCase().equals(Category.ROUTINE.name());
    }

}
