package com.demo6.shop.controller.client;


import com.demo6.shop.common.ICommon;
import com.demo6.shop.service.CategoryService;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class HomeController {
@Autowired
private ICommon iCommon;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/client/home")
    public String search(HttpServletRequest request,
                         @RequestParam(name="categoryId", required = false) Long categoryId,
                         @RequestParam(name = "pricing", required = false) String pricing,
                         @RequestParam(name = "sort", required = false) String sort,
                         @RequestParam(name = "text", required = false) String text,
                         @RequestParam(name="pageSize",required = false) Integer pageSizeCurrent,
                         @RequestParam(name="pageIndex",required = false) Integer pageIndex) {
        int pageSize = 6;
        float priceFrom = 0;
        float priceTo = 0;

        if (pricing != null) {
            switch (pricing) {
                case "under50":
                    priceTo = 50;
                    break;
                case "50to70":
                    priceFrom = 50;
                    priceTo = 70;
                    break;
                case "greaterthan70":
                    priceFrom = 70;
                    priceTo = 1000;
                    break;
            }
        } else {
            pricing = "default";
        }

     /*   if (categoryId != null) {
            categoryId = Long.parseLong(request.getParameter("categoryId"));
        }*/

      /*  if (request.getParameter("pageSize") != null && !request.getParameter("pageSize") .equals("") ) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
*/
       pageSize=Optional.ofNullable(pageSizeCurrent).orElse(pageSize);
        pageIndex= Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.countBySearch(categoryId, pricing, priceFrom, priceTo, text);
        Integer totalPage =  iCommon.totalPage(count,pageSize);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("text", text);
        request.setAttribute("sort", sort);
        request.setAttribute("pricing", pricing);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("products", productService.search(categoryId, pricing, priceFrom, priceTo, sort, text, pageIndex, pageSize));
        return "client/product_grid";
    }
}
