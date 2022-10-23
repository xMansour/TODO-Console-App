package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.Criteria;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        var result = MenuPrinter.printMainMenu();
        switch (result) {
            case 1:
                ToDo todo = MenuPrinter.createToDoMenu();
                toDoService.createToDo(todo);
                MenuPrinter.resetMenu();
                break;
            case 2:
                ToDo toDo = MenuPrinter.createToDoMenu();
                toDoService.updateToDo(toDo);
                break;
            case 3:
                String deletedToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                toDoService.deleteToDo(deletedToDoTitle);
                MenuPrinter.resetMenu();
                break;
            case 4:
                MenuPrinter.printResults(toDoService.selectAllToDos());
                MenuPrinter.printMainMenu();
                break;
            case 5: // Show Top 5
                break;
            case 6: // Filter by title
            {
                try {
                    var selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    var selectedTodo = toDoService.findByTitle(selectedTitle);
                    MenuPrinter.printResults(selectedTodo);
                    MenuPrinter.resetMenu();
                } catch (ToDoNotFoundException e) {
                    System.out.println(e.getMessage());
                    MenuPrinter.resetMenu();
                }
            }
            break;
            case 7: // Filter by start date
            {
                try {
                    var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    var selectedToDos = toDoService.findByDate(0, selectedStartDate);
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();
                } catch (InvalidDateFormatException e) {
                    System.out.println(e.getMessage());
                    MenuPrinter.resetMenu();
                }
            }
            break;
            case 8: // Filter by end date
            {
                try {
                    var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    var selectedToDos = toDoService.findByDate(1, selectedStartDate);
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();
                } catch (InvalidDateFormatException e) {
                    System.out.println(e.getMessage());
                    MenuPrinter.resetMenu();
                }
            }
            break;
            case 9: // Filter by priority
            {
                try {
                    var selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());
                    var selectedToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();

                } catch (PriorityNotFoundException e) {
                    System.out.println(e.getMessage());
                    MenuPrinter.resetMenu();
                }
            }
            break;
            case 10: // Exit
                System.exit(0);
                break;
        }

    }
}
