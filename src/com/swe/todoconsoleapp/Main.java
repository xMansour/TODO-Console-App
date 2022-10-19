package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) throws Exception {


        try {
            var selectedTitle = MenuPrinter.printFindByTitleMenu();
            var selectedTodo = toDoService.findAllByTitle(selectedTitle);
            MenuPrinter.printFindByTitleResult(selectedTodo);
        } catch (ToDoNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
