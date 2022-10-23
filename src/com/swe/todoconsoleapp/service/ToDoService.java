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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToDoService {
    public ToDo findByTitle(String title) throws ToDoNotFoundException {
        List<ToDo> toDos = selectAllToDos();

        if (toDos != null)
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

        if (toDos != null) for (var toDo : toDos) {
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
                if (toDos != null) for (var toDo : toDos) {
                    if (toDo.getStartDate() != null && toDo.getStartDate().equals(selectedDate))
                        result.add(toDo);
                }
            } else {
                if (toDos != null) for (var toDo : toDos) {
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
        createFileIfNotExists();
        if (!isEmptyFile()) {
            try (FileInputStream fileInputStream = new FileInputStream("todos");
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (List<ToDo>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void createToDo(ToDo todo) {
        List<ToDo> todos = selectAllToDos();
        if (todos == null)
            todos = new ArrayList<>();
        todos.add(todo);
        saveToDos(todos);
    }

    public void updateToDo(ToDo todo) {
        List<ToDo> todos = selectAllToDos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getTitle().equals(todo.getTitle())) {
                todos.get(i).setTitle(todo.getTitle());
                todos.get(i).setCategory(todo.getCategory());
                todos.get(i).setDescription(todo.getDescription());
                todos.get(i).setPriority(todo.getPriority());
                todos.get(i).setStartDate(todo.getStartDate());
                todos.get(i).setEndDate(todo.getEndDate());
            }
            saveToDos(todos);

        }


    }

    public void deleteToDo(String title) {
        List<ToDo> todos = selectAllToDos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getTitle().equals(title)) {
                todos.remove(todos.get(i));
            }
            saveToDos(todos);
        }
    }

    private void saveToDos(List<ToDo> todos) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("todos");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(todos);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void createFileIfNotExists() {
        File todosFile = new File("todos");
        if (!todosFile.exists()) {
            try {
                todosFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isEmptyFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("todos"))) {
            if (bufferedReader.readLine() == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ToDo> selectTopFiveNearestByStartDate() {
        List<ToDo> unSortedToDos = selectAllToDos();

        Collections.sort(unSortedToDos, new Comparator<ToDo>() {
            @Override
            public int compare(ToDo toDo1, ToDo toDo2) {
                return (toDo1.getStartDate().compareTo(toDo2.getStartDate()));
            }
        });

        List<ToDo> sortedToDos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sortedToDos.set(i, unSortedToDos.get(i));
        }
        return sortedToDos;
    }


}
