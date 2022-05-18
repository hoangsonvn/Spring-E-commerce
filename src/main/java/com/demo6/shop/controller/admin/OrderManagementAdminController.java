package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.model.CategoryDTO;
import com.demo6.shop.model.OrderDTO;
import com.demo6.shop.service.ItemService;
import com.demo6.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class OrderManagementAdminController {
    @Autowired
    private ICommon iCommon;
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/order-list")
    public String findAll(HttpServletRequest request, @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
       pageIndex= Optional.ofNullable(pageIndex).orElse(0);
        int count = orderService.count();
        int totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("orders", orderService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/order/order_list";
    }

    @GetMapping(value = "order-updateHome")
    public String orderUpdateHome(HttpServletRequest request,@RequestParam(value = "orderId") long orderId) {
        orderService.updateHome(orderId);
      /*  OrderDTO orderDTO = orderService.findById(orderId);
        orderDTO.setStatus("SUCCESS");
        orderService.update(orderDTO);*/
        return "redirect:/admin/home";
    }


    @GetMapping(value = "order-update")
    public String orderUpdate(HttpServletRequest request,@RequestParam(value = "orderId") long orderId) {
       orderService.updateHome(orderId);
        /* OrderDTO orderDTO = orderService.findById(orderId);
        orderDTO.setStatus("SUCCESS");
        orderService.update(orderDTO);*/
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping(value = "order-details")
    public String orderDetails(HttpServletRequest request,@RequestParam(value = "orderId") long orderId) {
        request.setAttribute("items", itemService.findByOrderId(orderId));
        request.setAttribute("order", orderService.findById(orderId));
        return "admin/order/order_details";
    }

    @PostMapping("/order-delete")
    public String orderDelete(HttpServletRequest request) {
        String[] orderIds = request.getParameterValues("orderId");
        for (String orderId : orderIds) {
            orderService.delete(Long.parseLong(orderId));
        }
        return "redirect:/admin/order-list";

    }
}
