package com.demo6.shop.convert;

import com.demo6.shop.entity.Category;
import com.demo6.shop.model.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        category.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }
}
