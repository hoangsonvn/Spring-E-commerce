package com.demo6.shop.dao;

import com.demo6.shop.entity.Item;

import java.util.List;

public interface ItemDao {

	void insert(Item item);
	
	void update(Item item);
	
	void delete(long itemId);
	
	List<Item> findAll(int pageIndex, int pageSize);
	
	List<Item> findByOrderId(long orderId);
}
