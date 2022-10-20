package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.ToDo;

import java.util.List;
import java.util.Scanner;

public class MenuPrinter {
    public static String printFindByMenu(String criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter todo " + criteria + ": ");
        String selectedCriteria = scanner.nextLine();
        return selectedCriteria;
    }

    public static void printToDo(ToDo toDo) {
        System.out.println("title: " + toDo.getTitle());
        System.out.println("description: " + toDo.getTitle());
        System.out.println("start date: " + toDo.getStartDate());
        System.out.println("end date: " + toDo.getEndDate());
        System.out.println("priority: " + toDo.getPriority().name());
        System.out.println("category: " + toDo.getCategory().name());
        System.out.println("--------");
    }

    public static void PrintToDos(List<ToDo> toDos) {
        for (var toDo : toDos
        ) {
            printToDo(toDo);
        }
    }

}
