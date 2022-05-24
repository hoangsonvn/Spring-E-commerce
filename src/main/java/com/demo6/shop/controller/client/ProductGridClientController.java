package com.demo6.shop.controller.client;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
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
    @Autowired
    private ICommon iCommon;

    @GetMapping(value = "/product-grid")
    public String productGrid(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId,
                              @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = pageIndex == null ? 0 : pageIndex;
        int count = productService.countByCategoryId(categoryId);
        Integer totalPage = iCommon.totalPage(count, SystemConstant.FEE);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("products", productService.findAllByCategoryId(categoryId, pageIndex, SystemConstant.PAGESIZE));
        return "client/product_grid";
    }

}
