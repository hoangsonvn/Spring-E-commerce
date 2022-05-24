package com.demo6.shop.service;

import com.demo6.shop.entity.Order;
import com.demo6.shop.dto.OrderDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    void updateHome(long orderId);
    Double totalPriceByCurrentMonth();
    Double totalPrice();

    Order insert(HttpSession session, OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(long orderId);

    List<OrderDTO> findAll(int pageIndex, int pageSize);

    List<OrderDTO> findByBuyer(long userId);

    int count();

    OrderDTO findById(long orderId);

}
