package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.Priority;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoService {
    public ToDo findAllByTitle(String title) throws ToDoNotFoundException {
        List<ToDo> temp = new ArrayList<>();
        ToDo result;
        var toDo = new ToDo("x", "testt", new Date(), new Date(), Priority.High, Category.Hobby);
        var toDo2 = new ToDo("y", "testt", new Date(), new Date(), Priority.High, Category.Hobby);
        var toDo3 = new ToDo("z", "testt", new Date(), new Date(), Priority.High, Category.Hobby);

        temp.add(toDo2);
        temp.add(toDo);
        temp.add(toDo3);

        for (var item : temp
        ) {
            if (item.getTitle().equals(title))
                return item;
        }
        throw new ToDoNotFoundException("No ToDo found with the selected title: " + title);
    }

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
