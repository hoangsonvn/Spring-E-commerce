package com.demo6.shop.controller.admin;

import com.demo6.shop.dto.CategoryDTO;
import com.demo6.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class CategoryManagementAdminController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listCategory")
    public String listCategory(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.findAll());
        return "admin/category/listCategory";
    }

    @GetMapping("/createCategory")
    public String createCategory() {
        return "admin/category/createCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.persist(categoryDTO);
        return "redirect:/admin/listCategory";
    }
    @GetMapping( "/updateCategory/{categoryId}")
    public String updateCategory1(HttpServletRequest request,@PathVariable("categoryId") Long categoryId) {
        request.setAttribute("category", categoryService.findOne(categoryId));
        return "admin/category/updateCategory";
    }
    @PostMapping("/updateCategory")
    public String updateCateogory(@ModelAttribute CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return "redirect:/admin/listCategory";
    }


    @PostMapping("/deleteCategory")
    public String deleteCategory(HttpServletRequest request) {
        String[] ids = request.getParameterValues("categoryId");
        for (String id : ids) {
            categoryService.delete(Long.valueOf(id));
        }
        return "redirect:/admin/listCategory";

    }
}
