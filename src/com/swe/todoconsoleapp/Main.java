package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.Criteria;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) throws Exception {
        try {
            var selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
            var selectedTodo = toDoService.findByTitle(selectedTitle);
            MenuPrinter.printResults(selectedTodo);
        } catch (ToDoNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            var selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());
            var selectedToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
            MenuPrinter.printResults(selectedToDos);
        } catch (PriorityNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.STARTDATE.name());
            var selectedToDos = toDoService.findByDate(0, selectedStartDate);
            MenuPrinter.printResults(selectedToDos);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
        try {
            var selectedEndDate = MenuPrinter.printFindByMenu(Criteria.ENDDATE.name());
            var selectedToDos = toDoService.findByDate(1, selectedEndDate);
            MenuPrinter.printResults(selectedToDos);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }

    }

}
