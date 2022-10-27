package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.Criteria;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.InputValidator;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        while (true) {
            var result = MenuPrinter.printMainMenu();
            switch (result) {
                case 1 -> {
                    ToDo todo = MenuPrinter.createToDoMenu();
                    toDoService.createToDo(todo);
                    MenuPrinter.resetMenu();
                }
                case 2 -> {
                    ToDo toDo = MenuPrinter.createToDoMenu();
                    toDoService.updateToDo(toDo);
                }
                case 3 -> {
                    String deletedToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    toDoService.deleteToDo(deletedToDoTitle);
                    MenuPrinter.resetMenu();
                }
                case 4 -> MenuPrinter.printResults(toDoService.selectAllToDos());
                case 5 -> MenuPrinter.printResults(toDoService.selectTopFiveNearestByStartDate());
                case 6 -> // Filter by title
                {

                    var selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    var selectedTodo = toDoService.findByTitle(selectedTitle);
                    if (selectedTodo != null) {
                        MenuPrinter.printResults(selectedTodo);
                        MenuPrinter.resetMenu();
                    } else {
                        System.out.println("No todo found with title: " + selectedTitle);
                        MenuPrinter.resetMenu();
                    }

                }
                case 7 -> // Filter by start date
                {
                    var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    var selectedToDos = toDoService.findByDate(0, selectedStartDate);
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();
                }
                case 8 -> // Filter by end date
                {
                    var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    var selectedToDos = toDoService.findByDate(1, selectedStartDate);
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();
                }
                case 9 -> // Filter by priority
                {
                    var selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());

                    if (!InputValidator.isValidPriority(selectedPriority)) {
                        System.out.println("selected priority not found: " + selectedPriority);
                        break;
                    }

                    var selectedToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
                    if (selectedToDos != null) {
                        MenuPrinter.printResults(selectedToDos);
                        MenuPrinter.resetMenu();
                    } else {
                        System.out.println("No todos found with the selected priority: " + selectedPriority);
                        MenuPrinter.resetMenu();
                    }
                }
                case 10 -> // update category
                {
                    //   String updateToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    //String updateToDoCategory = MenuPrinter.printFindByMenu(Criteria.CATEGORY.name());
                    String[] updatedValue = MenuPrinter.updateCategory();
                    if (updatedValue != null) {
                        String updateToDoTitle = updatedValue[0];
                        String updateToDoCategory = updatedValue[1];
                        var isAdded = toDoService.addItemToCategory(updateToDoTitle, updateToDoCategory);
                        if (isAdded)
                            System.out.println("==========category was updated===============");
                        else
                            System.out.println("==========category wasn't updated===============");
                    }

                }
                case 11 -> // add to favourite
                {
                    String updateToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    var isAdded = toDoService.addItemToFavourite(updateToDoTitle);
                    if (isAdded)
                        System.out.println("Item has been added successfully.");
                    System.out.println("no to found with title: " + updateToDoTitle);
                }
                case 12 -> // Exit
                        System.exit(0);
            }

        }
    }

}
