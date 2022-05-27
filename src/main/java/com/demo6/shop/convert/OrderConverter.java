package com.demo6.shop.convert;

import com.demo6.shop.dto.UserDTO;
import com.demo6.shop.entity.Order;
import com.demo6.shop.entity.User;
import com.demo6.shop.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public OrderDTO toDto(Order order) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(order.getBuyer().getUserId());
        userDTO.setEmail(order.getBuyer().getEmail());
        userDTO.setAddress(order.getBuyer().getAddress());
        userDTO.setPhone(order.getBuyer().getPhone());
        userDTO.setFullname(order.getBuyer().getFullname());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setBuyDate(order.getBuyDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPriceTotal(order.getPriceTotal());
        orderDTO.setUserDTO(userDTO);
        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setBuyDate(orderDTO.getBuyDate());
        order.setStatus(orderDTO.getStatus());
        order.setPriceTotal(orderDTO.getPriceTotal());

        User user = new User();
        user.setUserId(orderDTO.getUserDTO().getUserId());
        user.setAddress(orderDTO.getUserDTO().getAddress());
        user.setFullname(orderDTO.getUserDTO().getFullname());
        user.setPhone(orderDTO.getUserDTO().getPhone());
        order.setBuyer(user);
        return order;
    }


}

