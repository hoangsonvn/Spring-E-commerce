package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.dto.CategoryDTO;
import com.demo6.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value = "/admin")
public class CategoryManagementAdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ICommon iCommon;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @GetMapping("/listCategory")
    public String listCategory(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.findAll());
        iCommon.notificate(request);
        return "admin/category/listCategory";
    }

    @GetMapping("/createCategory")
    public String createCategory(HttpServletRequest request) {
        String message = request.getParameter("duplicate");
        if (message != null) {
            request.setAttribute("duplicate", resourceBundle.getString(message));
        }
        return "admin/category/createCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute CategoryDTO categoryDTO) {
        String categoryName=categoryService.findOneByCategoryName(categoryDTO.getCategoryName());
        if(categoryName!=null){
            return "redirect:/admin/createCategory?duplicate=duplicateCategoryName";
        }
        categoryService.persist(categoryDTO);
        return "redirect:/admin/listCategory?messagecreate=categoryCreate";
    }

    @GetMapping("/updateCategory/{categoryId}")
    public String updateCategory1(HttpServletRequest request, @PathVariable("categoryId") Long categoryId) {
        String message = request.getParameter("duplicate");
        if (message != null) {
            request.setAttribute("duplicate", resourceBundle.getString(message));
        }
        request.setAttribute("category", categoryService.findOne(categoryId));
        return "admin/category/updateCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCateogory(HttpServletRequest request,@ModelAttribute CategoryDTO categoryDTO) {
        String categoryName=categoryService.findOneByCategoryName(categoryDTO.getCategoryName());
       if(categoryName!=null && !categoryName.equals(categoryDTO.getCategoryName())){
           return "redirect:/admin/updateCategory/"+categoryDTO.getCategoryId()+"?duplicate=duplicateCategoryName";
       }
        categoryService.update(categoryDTO);
        return "redirect:/admin/listCategory?messageupdate=categoryUpdate";
    }


    @PostMapping("/deleteCategory")
    public String deleteCategory(HttpServletRequest request) {
        String[] ids = request.getParameterValues("categoryId");
        if(ids == null){
            return "redirect:/admin/listCategory?tick=tick";
        }
        for (String id : ids) {
            categoryService.delete(Long.valueOf(id));
        }
        return "redirect:/admin/listCategory?messagedelete=categoryDelete";

    }
}
