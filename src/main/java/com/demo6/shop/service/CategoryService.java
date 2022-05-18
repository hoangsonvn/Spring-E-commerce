package com.demo6.shop.service;

import com.demo6.shop.model.CategoryDTO;
import com.demo6.shop.model.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    void persist(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    CategoryDTO findOne(Long id);

    void delete(Long id);
}
