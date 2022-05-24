package com.demo6.shop.controller.admin;

import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.CategoryService;
import com.demo6.shop.service.OrderService;
import com.demo6.shop.service.ProductService;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class HomeAdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/home")
    public String home(HttpServletRequest request) {
        Double totalPrice = orderService.totalPrice() == null ? 0 : orderService.totalPrice();
        Double totalPriceByCurrentMonth = orderService.totalPriceByCurrentMonth() == null ? 0 : orderService.totalPriceByCurrentMonth();
        request.setAttribute("orders", orderService.findAll(0, SystemConstant.PAGESIZE));
        request.setAttribute("countorder", orderService.count());
        request.setAttribute("coutproduct", productService.count());
        request.setAttribute("count", userService.count());
        request.setAttribute("countcategory", categoryService.count());
        request.setAttribute("totalprice", totalPrice);
        request.setAttribute("totalpricebycurrentmonth", totalPriceByCurrentMonth);
        return "admin/home";
    }


}
