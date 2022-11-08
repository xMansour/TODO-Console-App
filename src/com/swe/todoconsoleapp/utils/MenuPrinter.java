package com.swe.todoconsoleapp.utils;

import com.swe.todoconsoleapp.entity.enums.Category;
import com.swe.todoconsoleapp.entity.enums.Priority;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.NoDateAssignedException;
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
            System.out.println("priority: " + toDo.getPriority().getName());
            System.out.println("category: " + toDo.getCategory().getName());
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
        else for (ToDo toDo : toDos) {
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

        Date startDate = Helpers.covertStringToDate(scanner.nextLine());

        if (!InputValidator.isValidStartDate(startDate))
            System.out.println("Date must follow dd/MM/yyyy and be after today");

        System.out.println();

        System.out.print("Please Enter ToDO's End Date: ");
        Date endDate = Helpers.covertStringToDate(scanner.nextLine());

        if (!InputValidator.isValidEndDate(endDate, startDate))
            System.out.println("Date must follow dd/MM/yyyy and be after start date");

        System.out.print("Please Enter ToDO's Priority from(HIGH, MEDIUM , LOW): ");
        String priority = scanner.nextLine();

        if (!InputValidator.isValidPriority(priority)) {
            System.out.println("Invalid priority and it will be set to LOW");
            priority = "LOW";
            // get low priority from PriorityService
        }
        System.out.print("Please Enter ToDO's Category from(work,hobby , routine): ");
        String category = scanner.nextLine();
        if (!InputValidator.isValidCategory(category)) {
            System.out.println(" Invalid Category and Category will set as DEFAULT");
            category = Category.DEFAULT.name();
            // get default caategory from CategoryService
        }
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

        // set priority and category objects instead of null
        return new ToDo(title, description, startDate, endDate, null, null, favourite);
    }

    public static String[] updateCategory() {
        ToDoService toDoService = new ToDoService();
        String[] updatedValue = new String[2];
        System.out.println("==========");
        System.out.print("Please Enter ToDO's Title you want to update Category: ");

        String title = scanner.nextLine();
        ToDo selectedTodo = toDoService.findByTitle(title);
        if (selectedTodo != null) {
            System.out.println();
            System.out.print("Please Enter ToDO's  new Category from(work,hobby,routine): ");
            String category = scanner.nextLine();
            if (!InputValidator.isValidCategory(category)) {
                System.out.println(" Invalid Category and Category will set as DEFAULT");
                category = Category.DEFAULT.name();
            }
            updatedValue[0] = title;
            updatedValue[1] = category;
            return updatedValue;
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
