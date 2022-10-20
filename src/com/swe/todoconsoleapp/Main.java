package com.swe.todoconsoleapp;

import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.service.ToDoService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.service.ToDoService;
import com.swe.todoconsoleapp.utils.MenuPrinter;

public class Main {

    private static final ToDoService toDoService = new ToDoService();

    public static void main(String[] args) throws Exception{
        ToDoService toDoService = new ToDoService();

        var toDo1 = new ToDo();
        toDo1.setTitle("todo1");
        toDoService.writeToDo(toDo1);


        try {
            var selectedTitle = MenuPrinter.printFindByTitleMenu();
            var selectedTodo = toDoService.findAllByTitle(selectedTitle);
            MenuPrinter.printFindByTitleResult(selectedTodo);
        } catch (ToDoNotFoundException e) {
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
