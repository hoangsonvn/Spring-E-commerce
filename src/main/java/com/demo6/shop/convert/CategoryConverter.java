package com.demo6.shop.convert;

import com.demo6.shop.entity.Category;
import com.demo6.shop.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setTitle(categoryDTO.getTitle());
        category.setContent(categoryDTO.getContent());
        return category;
    }

    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setContent(category.getContent());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }
}
