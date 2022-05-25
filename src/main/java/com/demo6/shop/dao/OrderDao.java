package com.demo6.shop.dao;

import com.demo6.shop.entity.Order;

import java.util.List;

public interface OrderDao {
    void deleteByUserId(Long id);

    Double totalPriceByCurrentMonth();

    Double totalPrice();

    void insert(Order order);

    void update(Order order);

    void delete(long orderId);

    List<Order> findAll(int pageIndex, int pageSize);

    List<Order> findByBuyer(long userId);

    int count();

    Order findById(long orderId);

}
