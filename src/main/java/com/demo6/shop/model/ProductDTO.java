package com.demo6.shop.model;

import java.util.Objects;

public class ProductDTO {

	private long productId;
	private String productName;
	private float price;
	private int quantity;
	private String description;
	private String image;
	private CategoryDTO categoryDTO;
	private SaleDTO saleDTO;
	
	
	public ProductDTO() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductDTO that = (ProductDTO) o;
		return productId == that.productId && Float.compare(that.price, price) == 0 && quantity == that.quantity && Objects.equals(productName, that.productName) && Objects.equals(description, that.description) && Objects.equals(image, that.image) && Objects.equals(categoryDTO, that.categoryDTO) && Objects.equals(saleDTO, that.saleDTO);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productName, price, quantity, description, image, categoryDTO, saleDTO);
	}

	public ProductDTO(long productId, String productName, float price, int quantity, String description, String image,
					  CategoryDTO categoryDTO, SaleDTO saleDTO) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.image = image;
		this.categoryDTO = categoryDTO;
		this.saleDTO = saleDTO;
	}

	public ProductDTO(String productName, float price, int quantity, String description, String image, CategoryDTO categoryDTO, SaleDTO saleDTO) {
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.image = image;
		this.categoryDTO = categoryDTO;
		this.saleDTO = saleDTO;
	}

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}


	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}


	public SaleDTO getSaleDTO() {
		return saleDTO;
	}


	public void setSaleDTO(SaleDTO saleDTO) {
		this.saleDTO = saleDTO;
	}


}
