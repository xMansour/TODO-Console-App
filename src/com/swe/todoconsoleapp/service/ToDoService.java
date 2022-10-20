package com.swe.todoconsoleapp.service;


import com.swe.todoconsoleapp.entity.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    public List<ToDo> selectAllToDos() {
        try (FileInputStream fileInputStream = new FileInputStream("todos");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<ToDo>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToDo(ToDo todo) {
        List<ToDo> todos = selectAllToDos();
        try (FileOutputStream fileOutputStream = new FileOutputStream("todos");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            if (todos == null)
                todos = new ArrayList<>();
            todos.add(todo);
            objectOutputStream.writeObject(todos);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
