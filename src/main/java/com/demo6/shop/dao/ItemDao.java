package com.demo6.shop.dao;

import com.demo6.shop.entity.Item;

import java.util.List;

public interface ItemDao {
	void insert(Item item);

	List<Item> findByOrderId(long orderId);
}
