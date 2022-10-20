package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.Priority;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.repository.ToDoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoService implements ToDoRepository {
    public ToDo findByTitle(String title) throws ToDoNotFoundException {
        List<ToDo> toDos = new ArrayList<>();
        var toDo = new ToDo("x", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);
        var toDo2 = new ToDo("y", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);
        var toDo3 = new ToDo("z", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);

        toDos.add(toDo2);
        toDos.add(toDo);
        toDos.add(toDo3);

        for (var item : toDos
        ) {
            if (item.getTitle().equals(title))
                return item;
        }
        throw new ToDoNotFoundException("No ToDo found with the selected title: " + title);
    }

    public List<ToDo> findByPriority(String priority) throws PriorityNotFoundException {
        List<ToDo> toDos = new ArrayList<>();
        List<ToDo> result = new ArrayList<>();
        var toDo1 = new ToDo("x", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);
        var toDo2 = new ToDo("y", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);
        var toDo3 = new ToDo("z", "testt", new Date(), new Date(), Priority.HIGH, Category.Hobby);

        toDos.add(toDo2);
        toDos.add(toDo1);
        toDos.add(toDo3);

        if (!InputValidator.isValidPriority(priority))
            throw new PriorityNotFoundException("Invalid Priority please select from(High,Medium,Low)");

        for (var toDo : toDos
        ) {
            if (toDo.getPriority().name().equals(priority))
                result.add(toDo);
        }
        return result;
    }

    public List<ToDo> findByStartDate(Date startDate)
    {

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
