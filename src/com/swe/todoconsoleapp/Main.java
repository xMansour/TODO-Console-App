package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.Criteria;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        try {
            var selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
            var selectedTodo = toDoService.findByTitle(selectedTitle);
            MenuPrinter.printToDo(selectedTodo);
        } catch (ToDoNotFoundException e) {
            System.out.println(e.getMessage());
        }

        var selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());

        try {
            var selectedToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
            MenuPrinter.PrintToDos(selectedToDos);
        } catch (PriorityNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
