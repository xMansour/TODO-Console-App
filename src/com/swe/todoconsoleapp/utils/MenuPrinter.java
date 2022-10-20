package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.ToDo;

import java.util.List;
import java.util.Scanner;

public class MenuPrinter {
    public static String printFindByMenu(String criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter todo " + criteria + ": ");
        return scanner.nextLine();
    }

    public static void printToDo(ToDo toDo) {
        System.out.println("title: " + toDo.getTitle());
        System.out.println("description: " + toDo.getTitle());
        System.out.println("start date: " + toDo.getStartDate());
        System.out.println("end date: " + toDo.getEndDate());

        if (toDo.getCategory() != null) {
            System.out.println("priority: " + toDo.getPriority().name());
            System.out.println("category: " + toDo.getCategory().name());
        } else {
            System.out.println("priority: Not assigned yet");
            System.out.println("category: Not assigned yet");
        }

        System.out.println("--------");
    }

    public static void PrintToDos(List<ToDo> toDos) {
        if (toDos.size() == 0)
            System.out.println("No toDos found");
        else
            for (var toDo : toDos
            ) {
                printToDo(toDo);
            }
    }
}
