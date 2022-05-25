package com.demo6.shop.dao;

import com.demo6.shop.entity.Item;

import java.util.List;

public interface ItemDao {
	void delete(Long id);
	void insert(Item item);
	List<Item> findByOrderId(long orderId);
}
