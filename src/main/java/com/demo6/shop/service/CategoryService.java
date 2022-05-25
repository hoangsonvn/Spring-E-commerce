package com.demo6.shop.service;

import com.demo6.shop.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    String findOneByCategoryName(String categoryName);
    Long count();

    List<CategoryDTO> findAll();

    void persist(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    CategoryDTO findOne(Long id);

    void delete(Long id);
}
