package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.ToDo;

import java.util.Scanner;

public class MenuPrinter {
    public static String printFindByTitleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter todo title:");
        String selectedTitle = scanner.nextLine();
        return selectedTitle;
    }

    public static void printFindByTitleResult(ToDo result) {
        System.out.println("title: " + result.getTitle());
        System.out.println("description: " + result.getTitle());
        System.out.println("start date: " + result.getStartDate());
        System.out.println("end date: " + result.getEndDate());
        System.out.println("priority: " + result.getPriority().name());
        System.out.println("category: " + result.getCategory().name());
        System.out.println("--------");
    }
}
