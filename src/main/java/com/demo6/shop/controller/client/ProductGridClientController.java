package com.demo6.shop.controller.client;

import com.demo6.shop.service.CategoryService;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/client")
public class ProductGridClientController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product-grid")
    public String productGrid(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId
    ) {
        int pageIndex = 0;
        int pagesize = 6;
        int totalPage = 0;
        int count = productService.countByCategoryId(categoryId);
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        if (count % pagesize == 0) {
            totalPage = count / pagesize;
        } else {
            totalPage = count / pagesize + 1;
        }
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("products", productService.findAllByCategoryId(categoryId, pageIndex, pagesize));
        return "client/product_grid";
    }

}
