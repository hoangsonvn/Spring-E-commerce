package com.demo6.shop.controller.admin;

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
	@GetMapping(value = "/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("orders", orderService.findAll(0, 5));
		request.setAttribute("countorder",orderService.count());
		request.setAttribute("coutproduct",productService.count());
		request.setAttribute("count",userService.count());
		request.setAttribute("totalprice",orderService.totalPrice());
		request.setAttribute("totalpricebycurrentmonth",orderService.totalPriceByCurrentMonth());
		return "admin/home";
	}
	
	
}
