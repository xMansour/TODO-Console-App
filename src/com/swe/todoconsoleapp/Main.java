package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.enums.Criteria;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.InputValidator;
import com.swe.todoconsoleapp.utils.MenuPrinter;

import java.util.List;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) {
        while (true) {
            Integer result = MenuPrinter.printMainMenu();
            switch (result) {
                case 1:
                    ToDo todo = MenuPrinter.createToDoMenu();
                    toDoService.create(todo);
                    MenuPrinter.resetMenu();
                    break;
                case 2:
                    ToDo toDo = MenuPrinter.createToDoMenu();
                    toDoService.update(toDo);
                    break;
                case 3:
                    String deletedToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    toDoService.deleteByTitle(deletedToDoTitle);
                    MenuPrinter.resetMenu();
                    break;
                case 4:
                    MenuPrinter.printResults(toDoService.get());
                    break;
                case 5:
                    MenuPrinter.printResults(toDoService.selectTopFiveNearestByStartDate());
                    break;
// Filter by title
                case 6:
                    String selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    ToDo selectedTodo = toDoService.findByTitle(selectedTitle);
                    if (selectedTodo != null) {
                        MenuPrinter.printResults(selectedTodo);
                        MenuPrinter.resetMenu();
                    } else {
                        System.out.println("No todo found with title: " + selectedTitle);
                        MenuPrinter.resetMenu();
                    }
                    break;
// Filter by start date
                case 7:
                    String selectedStartDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    List<ToDo> selectedToDos = toDoService.findByDate(0, selectedStartDate);
                    MenuPrinter.printResults(selectedToDos);
                    MenuPrinter.resetMenu();
                    break;
// Filter by end date
                case 8:
                    String selectedDate = MenuPrinter.printFindByMenu(Criteria.START_DATE.name());
                    List<ToDo> toDos = toDoService.findByDate(1, selectedDate);
                    MenuPrinter.printResults(toDos);
                    MenuPrinter.resetMenu();
                    break;
// Filter by priority
                case 9:
                    String selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());
                    if (!InputValidator.isValidPriority(selectedPriority)) {
                        System.out.println("selected priority not found: " + selectedPriority);
                        break;
                    }
                    List<ToDo> priorityToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
                    if (priorityToDos != null) {
                        MenuPrinter.printResults(priorityToDos);
                        MenuPrinter.resetMenu();
                    } else {
                        System.out.println("No todos found with the selected priority: " + selectedPriority);
                        MenuPrinter.resetMenu();
                    }
                    break;
                case 10:
                    String[] updatedValue = MenuPrinter.updateCategory();
                    if (updatedValue != null) {
                        String updateToDoTitle = updatedValue[0];
                        String updateToDoCategory = updatedValue[1];
                        boolean isAdded = toDoService.addItemToCategory(updateToDoTitle, updateToDoCategory);
                        if (isAdded)
                            System.out.println("==========category was updated===============");
                        else
                            System.out.println("==========category wasn't updated===============");
                    }
                    break;
// add to favourite
                case 11:
                    String updateToDoTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
                    boolean isAdded = toDoService.addItemToFavourite(updateToDoTitle);
                    if (isAdded)
                        System.out.println("Item has been added successfully.");
                    System.out.println("no to found with title: " + updateToDoTitle);
                    break;
// Exit
                case 12:
                    System.exit(0);
            }

        }
    }

}
