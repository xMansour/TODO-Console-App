package com.swe.todoconsoleapp.repository;

import com.swe.todoconsoleapp.entity.Category;

public interface CategoryRepository  extends Repository<Category>{
    Category findCategoryByName(String name);
}
