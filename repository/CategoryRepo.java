package com.jdbc.repository;

import com.jdbc.model.Category;

import java.util.List;

public interface CategoryRepo {
    boolean save(Category category);

    boolean udpate(Category category);

    void delete(Category category);

    Category findById(int id);

    List<Category> findCategoryByName(String name);

    boolean checkIsExistCategory(String name);
}
