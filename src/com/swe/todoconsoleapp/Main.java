package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.Criteria;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

import java.util.List;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) throws Exception {
        try {
            var selectedTitle = MenuPrinter.printFindByMenu(Criteria.TITLE.name());
            var selectedTodo = toDoService.findByTitle(selectedTitle);
            MenuPrinter.printToDo(selectedTodo);
        } catch (ToDoNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            var selectedPriority = MenuPrinter.printFindByMenu(Criteria.PRIORITY.name());
            var selectedToDos = toDoService.findByPriority(selectedPriority.toUpperCase());
            MenuPrinter.PrintToDos(selectedToDos);
        } catch (PriorityNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            var selectedStartDate = MenuPrinter.printFindByMenu(Criteria.STARTDATE.name());
            var selectedToDos = toDoService.findByDate(0, selectedStartDate);
            MenuPrinter.PrintToDos(selectedToDos);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
        try {
            var selectedEndDate = MenuPrinter.printFindByMenu(Criteria.ENDDATE.name());
            var selectedToDos = toDoService.findByDate(1, selectedEndDate);
            MenuPrinter.PrintToDos(selectedToDos);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }

        var todo2 = new ToDo();
        todo2.setTitle("todo2");
        toDoService.writeToDo(todo2);

        var todo3 = new ToDo();
        todo3.setTitle("todo3");
        toDoService.writeToDo(todo3);


        List<ToDo> todos = toDoService.selectAllToDos();
        todos.forEach(todo -> {
            System.out.println(todo.getTitle());
        });


        /*FileOutputStream fileOutputStream = new FileOutputStream("x");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        List<ToDo> list = new ArrayList<>();
        list.add(new ToDo());
        list.add(new ToDo());
        list.add(new ToDo());
        objectOutputStream.writeObject(list);

        FileInputStream fileInputStream = new FileInputStream("x");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<ToDo> todos = (List<ToDo>)objectInputStream.readObject();
        todos.forEach(todo ->{
            System.out.println(todo.getTitle());
        });*/
    }

}
