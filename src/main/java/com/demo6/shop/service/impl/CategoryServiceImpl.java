package com.demo6.shop.service.impl;

import com.demo6.shop.convert.CategoryConverter;
import com.demo6.shop.dao.CategoryDao;
import com.demo6.shop.entity.Category;
import com.demo6.shop.entity.Product;
import com.demo6.shop.model.CategoryDTO;
import com.demo6.shop.model.ProductDTO;
import com.demo6.shop.model.SaleDTO;
import com.demo6.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryDao.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			CategoryDTO categoryDTO = new CategoryDTO();
			List<Product> products =	category.getProducts();
		//	products.forEach(s-> System.out.println(s.getPrice()));
			categoryDTO.setCategoryId(category.getCategoryId());
			categoryDTO.setCategoryName(category.getCategoryName());
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
