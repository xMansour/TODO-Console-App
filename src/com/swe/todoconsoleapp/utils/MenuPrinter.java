package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.Priority;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.NoDateAssignedException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuPrinter {

    private static final Scanner scanner = new Scanner(System.in);

    public static Integer printMainMenu() {
        String input = "";
        do {
            System.out.println("Please select from menu:");
            System.out.println("1- Create todo.");
            System.out.println("2- Edit todo.");
            System.out.println("3- Delete todo.");
            System.out.println("4- Show all todos.");
            System.out.println("5- Show top nearest 5 todos.");
            System.out.println("6- Filter by title.");
            System.out.println("7- Filter by start date.");
            System.out.println("8- Filter by end date.");
            System.out.println("9- Filter by priority.");
            System.out.println("10- Update Category.");
            System.out.println("11- Add to Favourite list.");
            System.out.println("12- Exit");
            input = scanner.nextLine();
        } while (!InputValidator.isValidNumber(input, 1, 12));
        return Integer.parseInt(input);
    }

    public static String printFindByMenu(String criteria) {
        System.out.println("Please enter todo " + criteria + ": ");
        return scanner.nextLine();
    }

    public static void printResults(ToDo toDo) {
        System.out.println("title: " + toDo.getTitle());
        System.out.println("description: " + toDo.getTitle());
        try {
            System.out.println("start date: " + Helpers.covertDateToString(toDo.getEndDate()));

        } catch (NoDateAssignedException ex) {
            System.out.println("start date: " + ex.getMessage());

        }
        try {
            System.out.println("end date: " + Helpers.covertDateToString(toDo.getStartDate()));
        } catch (NoDateAssignedException ex) {
            System.out.println("end date: " + ex.getMessage());
        }

        if (toDo.getCategory() != null) {
            System.out.println("priority: " + toDo.getPriority().name());
            System.out.println("category: " + toDo.getCategory().name());
        } else {
            System.out.println("priority: Not assigned yet");
            System.out.println("category: Not assigned yet");
        }
        if (toDo.isFavourite())
            System.out.println("favourite : YES");

        else {
            System.out.println("favourite : NO");

        }
        System.out.println("--------");
    }

    public static void printResults(List<ToDo> toDos) {

        if (toDos == null || toDos.size() == 0) System.out.println("No toDos found");
        else for (var toDo : toDos) {
            printResults(toDo);
        }
    }

    public static void resetMenu() {
        System.out.println("==========");
        System.out.println("press ENTER to return to main menu");
        scanner.nextLine();
    }

    public static ToDo createToDoMenu() {
        System.out.println("==========");
        System.out.print("Please Enter ToDO's Title: ");
        String title = scanner.nextLine();
        System.out.println();

        System.out.print("Please Enter ToDO's Description: ");
        String description = scanner.nextLine();
        System.out.println();

        System.out.print("Please Enter ToDO's Start Date: ");
        Date startDate = null;
        try {
            startDate = Helpers.covertStringToDate(scanner.nextLine());
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.print("Please Enter ToDO's End Date: ");
        Date endDate = null;

        try {
            endDate = Helpers.covertStringToDate(scanner.nextLine());
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.print("Please Enter ToDO's Priority from(HIGH, MEDIUM , LOW): ");

        String priority = scanner.nextLine();
        if (!InputValidator.isValidPriority(priority)) {
            System.out.println("Invalid priority and it will be set to LOW");
            priority = "LOW";
        }
        System.out.print("Please Enter ToDO's Category from(work,hobby , routine): ");
        String category = scanner.nextLine();
        System.out.println();
        System.out.println("Do you want to add this ToDo item to favourite list (YES or NO )");
        String answer = scanner.nextLine();
        boolean favourite = false;
        switch (answer) {
            case "yes":
            case "YES":
                favourite = true;
                break;
            case "no":
            case "No":
                favourite = false;
                break;

        }

        return new ToDo(title, description, startDate, endDate, Priority.valueOf(priority.toUpperCase()), Category.valueOf(category.toUpperCase()), favourite);
    }


    //    public static String updateCategory()
//    {
//        System.out.println("==========");
//        System.out.print("Please Enter ToDO's Title you want to update Category: ");
//        String title = scanner.nextLine();
//        System.out.println();
//        System.out.print("Please Enter ToDO's  new Category from(work,hobby , routine): ");
//        String category = scanner.nextLine();
//
//
//
//        System.out.println();
//        return title;
//
//
//    }
    public static String[] updateCategory() {
        ToDoService toDoService = new ToDoService();
        String[] updatedValue = new String[2];
        System.out.println("==========");
        System.out.print("Please Enter ToDO's Title you want to update Category: ");

        try {
            String title = scanner.nextLine();
            toDoService.findByTitle(title);
            System.out.println();
            System.out.print("Please Enter ToDO's  new Category from(work,hobby,routine): ");
            String category = scanner.nextLine();
            if (!InputValidator.isValidCategory(category)) {
                System.out.println(" no such category found");
                return null;
            }
            updatedValue[0] = title;
            updatedValue[1] = category;
            return updatedValue;
        } catch (ToDoNotFoundException e) {
            e.getMessage();
        }


        return null;

    }

    public static String addToFavourite() {
        System.out.println("==========");
        System.out.print("Please Enter ToDO's Title you want to add to favourite: ");
        String title = scanner.nextLine();
        return title;

    }


}
