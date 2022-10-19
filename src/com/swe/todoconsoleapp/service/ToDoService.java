package com.swe.todoconsoleapp.service;

import com.swe.todoconsoleapp.entity.Category;
import com.swe.todoconsoleapp.entity.Priority;
import com.swe.todoconsoleapp.entity.ToDo;
import com.swe.todoconsoleapp.exception.ToDoNotFoundException;
import com.swe.todoconsoleapp.repository.ToDoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoService implements ToDoRepository {
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
}
