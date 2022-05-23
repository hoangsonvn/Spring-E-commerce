package com.demo6.shop.controller.client;

import com.demo6.shop.service.CommentService;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/client")
public class ProductDetailsClientController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/product-details")
    public String productDetails(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
        request.setAttribute("comments", commentService.findAllByProductId(productId));
        request.setAttribute("product", productService.findById(productId));
        return "client/product_details";
    }
}
