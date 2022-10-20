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

public class Main {
    public static void main(String[] args) throws Exception{
        ToDoService toDoService = new ToDoService();

        var toDo1 = new ToDo();
        toDo1.setTitle("todo1");
        toDoService.writeToDo(toDo1);

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
