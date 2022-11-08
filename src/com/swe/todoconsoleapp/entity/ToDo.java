package com.swe.todoconsoleapp.entity;

import com.swe.todoconsoleapp.entity.enums.Category;
import com.swe.todoconsoleapp.entity.enums.Priority;

import java.io.Serializable;
import java.util.Date;

public class ToDo implements Serializable {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

    private Priority priority;
    private Category category;


    private boolean favourite;

    public ToDo(String title, String description, Date startDate, Date endDate, Priority priority, Category category , boolean favourite) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.category = category;
        this.favourite= favourite;
    }


    public ToDo() {
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
