package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.InvalidDateFormatException;
import com.swe.todoconsoleapp.exception.PriorityNotFoundException;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.utils.Helpers;
import com.swe.todoconsoleapp.utils.InputValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToDoService {
    private static final int SEARCH_BY_START_DATE = 0;

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

        var selectedDate = Helpers.covertStringToDate(date);
        if (mode == SEARCH_BY_START_DATE) {
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

        //if it is the first time to add a to-do
        if (todos == null)
            todos = new ArrayList<>();

        //to check if the to-do already exists
        boolean found = false;
        for (ToDo currentToDO : todos) {
            if (currentToDO.getTitle().equals(todo.getTitle())) {
                found = true;
                break;
            }
        }
        //if the to-do doesn't exist, add it
        if (!found)
            todos.add(todo);
        saveToDos(todos);
    }

    public boolean updateToDo(ToDo todo) {
        List<ToDo> todos = selectAllToDos();
        boolean updated = false;
        //loop and check if the to-do exists, update it
        for (ToDo currentToDo : todos) {
            if (currentToDo.getTitle().equals(todo.getTitle())) {
                currentToDo.setTitle(todo.getTitle());
                currentToDo.setCategory(todo.getCategory());
                currentToDo.setDescription(todo.getDescription());
                currentToDo.setPriority(todo.getPriority());
                currentToDo.setStartDate(todo.getStartDate());
                currentToDo.setEndDate(todo.getEndDate());
                updated = true;
                break;
            }
        }
        saveToDos(todos);
        return updated;

    }

    public boolean deleteToDo(String title) {
        List<ToDo> todos = selectAllToDos();
        boolean deleted = false;
        //todos.removeIf(currentToDo -> currentToDo.getTitle().equals(title));  //Collections.removeIf -> Amazing

        //to check if the to-do exists, remove it
        for (ToDo currentToDo : todos) {
            if (currentToDo.getTitle().equals(title)) {
                todos.remove(currentToDo);
                deleted = true;
                break;
            }
        }
        saveToDos(todos);
        return deleted;
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
