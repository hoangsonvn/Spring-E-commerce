package com.demo6.shop.service.impl;

import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.convert.OrderConverter;
import com.demo6.shop.dao.OrderDao;
import com.demo6.shop.entity.Order;
import com.demo6.shop.entity.User;
import com.demo6.shop.dto.OrderDTO;
import com.demo6.shop.dto.UserDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.service.OrderService;
import com.demo6.shop.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public void updateHome(long orderId) {
        OrderDTO orderDTO = this.findById(orderId);
        orderDTO.setStatus("SUCCESS");
        this.update(orderDTO);
    }

    @Override
    public Double totalPriceByCurrentMonth() {
        return orderDao.totalPriceByCurrentMonth();
    }

    @Override
    public Double totalPrice() {
        return orderDao.totalPrice();
    }

    @Override
    public Order insert(HttpSession session, OrderDTO orderDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userPrincipal.getUserId());
        userDTO.setPhone(userPrincipal.getPhone());
        userDTO.setAddress(userPrincipal.getAddress());
        userDTO.setFullname(userPrincipal.getFullname());

        orderDTO.setUserDTO(userDTO);
        orderDTO.setBuyDate(new Date(new java.util.Date().getTime()));
        orderDTO.setStatus("PENDING");
        orderDTO.setPriceTotal(SessionUtils.totalPriceSaleSession(session) + SystemConstant.FEE);
        Order order = orderConverter.toEntity(orderDTO);
        orderDao.insert(order);
        return order;

    }

    @Override
    public void update(OrderDTO orderDTO) {
        User user = new User();
        user.setUserId(orderDTO.getUserDTO().getUserId());
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setBuyDate(orderDTO.getBuyDate());
        order.setStatus(orderDTO.getStatus());
        order.setPriceTotal(orderDTO.getPriceTotal());
        order.setBuyer(user);
        orderDao.update(order);
    }

    @Override
    public void delete(long orderId) {
        orderDao.delete(orderId);
    }

    @Override
    public List<OrderDTO> findAll(int pageIndex, int pageSize) {
        List<Order> orders = orderDao.findAll(pageIndex, pageSize);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
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
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    public List<OrderDTO> findByBuyer(long userId) {
        List<Order> orders = orderDao.findByBuyer(userId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setBuyDate(order.getBuyDate());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setPriceTotal(order.getPriceTotal());
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    public int count() {
        return orderDao.count();
    }

    @Override
    public OrderDTO findById(long orderId) {
        Order order = orderDao.findById(orderId);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(order.getBuyer().getUserId());
        userDTO.setFullname(order.getBuyer().getFullname());
        userDTO.setAddress(order.getBuyer().getAddress());
        userDTO.setEmail(order.getBuyer().getEmail());
        userDTO.setPhone(order.getBuyer().getPhone());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setBuyDate(order.getBuyDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPriceTotal(order.getPriceTotal());
        orderDTO.setUserDTO(userDTO);
        return orderDTO;
    }

}
