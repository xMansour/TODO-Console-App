package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.utils.InputValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoService {
    public ToDo findByTitle(String title) throws ToDoNotFoundException {
        List<ToDo> toDos = selectAllToDos();

        for (var item : toDos
        ) {
            if (item.getTitle().equals(title))
                return item;
        }
        throw new ToDoNotFoundException("No ToDo found with the selected title: " + title);
    }

    public List<ToDo> findByPriority(String priority) throws PriorityNotFoundException {
        List<ToDo> toDos = selectAllToDos();
        List<ToDo> result = new ArrayList<>();
        if (!InputValidator.isValidPriority(priority))
            throw new PriorityNotFoundException("Invalid Priority please select from(High,Medium,Low)");

        for (var toDo : toDos
        ) {
            if (toDo.getPriority().name().equals(priority))
                result.add(toDo);
        }
        return result;
    }

    public List<ToDo> findByStartDate(Date startDate) {
        return null;
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
