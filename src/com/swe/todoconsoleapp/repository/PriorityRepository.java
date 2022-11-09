package com.swe.todoconsoleapp.repository;

import com.swe.todoconsoleapp.entity.Priority;

public interface PriorityRepository extends Repository<Priority> {
    Priority findPriorityByName(String name);
}
