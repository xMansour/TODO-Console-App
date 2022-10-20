package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.utils.InputValidator;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {
    public ToDo findByTitle(String title) throws ToDoNotFoundException {
        List<ToDo> toDos = selectAllToDos();

        for (var item : toDos) {
            if (item.getTitle().equals(title)) return item;
        }
        throw new ToDoNotFoundException("No ToDo found with the selected title: " + title);
    }

    public List<ToDo> findByPriority(String priority) throws PriorityNotFoundException {
        List<ToDo> toDos = selectAllToDos();
        List<ToDo> result = new ArrayList<>();
        if (!InputValidator.isValidPriority(priority))
            throw new PriorityNotFoundException("Invalid Priority please select from(High,Medium,Low)");

        if (toDos.size() > 0) for (var toDo : toDos) {
            if (toDo.getCategory() != null && toDo.getPriority().name().equals(priority)) result.add(toDo);
        }
        return result;
    }

    public List<ToDo> findByDate(int mode, String date) throws InvalidDateFormatException {

        var toDos = selectAllToDos();
        var result = new ArrayList<ToDo>();
        var simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            var selectedDate = simpleDateFormat.parse(date);
            if (mode == 0) {
                if (toDos.size() > 0) for (var toDo : toDos) {
                    if (toDo.getStartDate() != null && toDo.getStartDate().equals(selectedDate))
                        result.add(toDo);
                }
            } else {
                if (toDos.size() > 0) for (var toDo : toDos) {
                    if (toDo.getEndDate() != null && toDo.getEndDate().equals(selectedDate))
                        result.add(toDo);
                }
            }
        } catch (ParseException e) {
            throw new InvalidDateFormatException("date format must follow dd/MM/yyy");
        }
        return result;
    }

    public List<ToDo> selectAllToDos() {
        try (FileInputStream fileInputStream = new FileInputStream("todos"); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<ToDo>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToDo(ToDo todo) {
        List<ToDo> todos = selectAllToDos();
        try (FileOutputStream fileOutputStream = new FileOutputStream("todos"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            if (todos == null) todos = new ArrayList<>();
            todos.add(todo);
            objectOutputStream.writeObject(todos);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
