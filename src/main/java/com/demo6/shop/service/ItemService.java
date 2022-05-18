package com.demo6.shop.service;

import com.demo6.shop.model.ItemDTO;

import java.util.List;

public interface ItemService {

	void insert(ItemDTO itemDTO);
	
	void update(ItemDTO itemDTO);
	
	void delete(long itemId);
	
	List<ItemDTO> findAll(int pageIndex, int pageSize);
	
	List<ItemDTO> findByOrderId(long orderId);
	
}
