package com.demo6.shop.service;

import com.demo6.shop.dto.ItemDTO;

import java.util.List;

public interface ItemService {
	//void insert(ItemDTO itemDTO);
	List<ItemDTO> findByOrderId(long orderId);

}
