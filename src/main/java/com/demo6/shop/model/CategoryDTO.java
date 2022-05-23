package com.demo6.shop.model;

import java.util.List;

public class CategoryDTO {

	private long categoryId;
	private String categoryName;
	private List<ProductDTO> productDTOs;
	
	public CategoryDTO() {
	}

	/*public CategoryDTO(long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public CategoryDTO(long categoryId, String categoryName, List<ProductDTO> productDTOs) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productDTOs = productDTOs;
	}*/

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}

	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}
	
	
}
