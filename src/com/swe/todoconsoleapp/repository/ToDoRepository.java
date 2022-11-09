package com.swe.todoconsoleapp.repository;

import com.swe.todoconsoleapp.entity.ToDo;

import java.util.List;

public interface ToDoRepository extends Repository<ToDo> {
    boolean addItemToFavourite(String title);

    ToDo findByTitle(String title);

    boolean addItemToCategory(String title, String category);

    List<ToDo> selectTopFiveNearestByStartDate();

    List<ToDo> findByDate(int mode, String date);

    List<ToDo> findByPriority(String priority);

    boolean deleteByTitle(String title);
}
