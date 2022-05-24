package com.demo6.shop.dto;

import java.util.List;

public class SaleDTO {

	private String saleId;
	private int salePercent;
	private List<ProductDTO> productDTOs;
	
	public SaleDTO() {
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public int getSalePercent() {
		return salePercent;
	}

	public void setSalePercent(int salePercent) {
		this.salePercent = salePercent;
	}

	public List<ProductDTO> getProductDTOs() {
		return productDTOs;
	}

	public void setProductDTOs(List<ProductDTO> productDTOs) {
		this.productDTOs = productDTOs;
	}
	
	

}
