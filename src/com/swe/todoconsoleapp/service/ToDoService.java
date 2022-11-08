package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.utils.Helpers;
import com.swe.todoconsoleapp.utils.InputValidator;

import java.io.*;
import java.util.*;

public class ToDoService {
    private static final int SEARCH_BY_START_DATE = 0;

    public ToDo findByTitle(String title) {
        List<ToDo> toDos = selectAllToDos();
        ToDo selectedTodo = null;

        if (toDos != null)
            for (ToDo item : toDos) {
                if (item.getTitle().equals(title)) selectedTodo = item;
            }
        return selectedTodo;
    }

    public List<ToDo> findByPriority(String priority) {
        List<ToDo> toDos = selectAllToDos();
        List<ToDo> result = new ArrayList<>();
        if (InputValidator.isValidPriority(priority))
            return null;

        if (toDos != null) for (ToDo toDo : toDos) {
            if (toDo.getCategory() != null && toDo.getPriority().name().equals(priority)) result.add(toDo);
        }
        return result;
    }

    public List<ToDo> findByDate(int mode, String date) {
        List<ToDo> toDos = selectAllToDos();
        List<ToDo> result = new ArrayList<ToDo>();

        Date selectedDate = Helpers.covertStringToDate(date);
        if (mode == SEARCH_BY_START_DATE) {
            if (toDos != null) for (ToDo toDo : toDos) {
                if (toDo.getStartDate() != null && toDo.getStartDate().equals(selectedDate))
                    result.add(toDo);
            }
        } else {
            if (toDos != null) for (ToDo toDo : toDos) {
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
                currentToDo.setFavourite(todo.isFavourite());

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


        if (unSortedToDos != null) {
            {
                Collections.sort(unSortedToDos, new Comparator<ToDo>() {
                    @Override
                    public int compare(ToDo toDo1, ToDo toDo2) {
                        return (toDo1.getStartDate().compareTo(toDo2.getStartDate()));
                    }
                });
            }
            List<ToDo> sortedToDos = new ArrayList<>();
            for (int i = 0; i < unSortedToDos.size(); i++) {
                sortedToDos.add(i, unSortedToDos.get(i));
            }
            return sortedToDos;
        }
        return unSortedToDos;
    }


    public boolean addItemToCategory(String title, String category) {

        ToDo toDo = findByTitle(title);
        if (toDo != null) {
            Category updatedCategory = Category.valueOf(category.toUpperCase());
            toDo.setCategory(updatedCategory);
            updateToDo(toDo);
            return true;
        }
        return false;
    }


    public boolean addItemToFavourite(String title) {
        ToDo toDo = findByTitle(title);
        if (toDo != null) {
            toDo.setFavourite(true);
            updateToDo(toDo);
            return true;
        }
        return false;
    }
}
