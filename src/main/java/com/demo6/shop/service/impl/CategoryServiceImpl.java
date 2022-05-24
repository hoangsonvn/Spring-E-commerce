package com.demo6.shop.service.impl;

import com.demo6.shop.convert.CategoryConverter;
import com.demo6.shop.dao.CategoryDao;
import com.demo6.shop.dto.CategoryDTO;
import com.demo6.shop.entity.Category;
import com.demo6.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private CategoryConverter categoryConverter;


	@Override
	public Long count() {
		return categoryDao.count();
	}

	@Override
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryDao.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(category.getCategoryId());
			categoryDTO.setCategoryName(category.getCategoryName());
			categoryDTO.setTitle(category.getTitle());
			categoryDTO.setContent(category.getContent());
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

	@Override
	public void persist(CategoryDTO categoryDTO) {
		categoryDao.persist(categoryConverter.toEntity(categoryDTO));

	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		 categoryDao.update(categoryConverter.toEntity(categoryDTO));
	}

	@Override
	public CategoryDTO findOne(Long id) {
		return categoryConverter.toDto(categoryDao.findOne(id));
	}

	@Override
	public void delete(Long id) {
		categoryDao.delete(id);
	}

}
