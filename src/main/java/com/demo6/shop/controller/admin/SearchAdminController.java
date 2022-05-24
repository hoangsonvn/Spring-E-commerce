package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SearchAdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ICommon iCommon;

    @PostMapping("/admin/search")
    public String search(HttpServletRequest request,
                         @RequestParam(name = "text", required = false) String text,
                         @RequestParam(name = "pageIndex", required = false) Integer pageIndex) {

        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = Math.toIntExact(productService.countSearch(text));
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("text", text);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("products", productService.search( text, pageIndex, SystemConstant.PAGESIZE));
        return "/admin/product/listProduct";
    }

}
