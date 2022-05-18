package com.demo6.shop.dao;

import com.demo6.shop.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category findOne(Long id);

    List<Category> findAll();

    void persist(Category category);

    Category update(Category category);
    void delete(Long id);
}
