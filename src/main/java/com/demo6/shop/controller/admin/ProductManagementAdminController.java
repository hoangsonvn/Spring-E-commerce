package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.CategoryService;
import com.demo6.shop.service.ProductService;
import com.demo6.shop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

// Product Manager

@Controller
@RequestMapping(value = "/admin")
public class ProductManagementAdminController {

    @Autowired
    private ICommon iCommon;
    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping(value = "/product-list")
    public String findAll(HttpServletRequest request, @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.count();
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("default", "default");
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("products", productService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/product/listProduct";
    }

    @GetMapping(value = "/product-list-by-category")
    public String findAllByCategory(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId,
                                    @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.countByCategoryId(categoryId);
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("products", productService.findAllByCategoryId(categoryId, pageIndex, SystemConstant.PAGESIZE));
        return "admin/product/listProductByCategory";
    }

    //@PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    @GetMapping(value = "/product-create")
    public String insert(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("sales", saleService.findAll());
        return "admin/product/createNewProduct";
    }

  //  @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    @PostMapping(value = "/product-create")
    public String insertPost(@RequestParam(name = "categoryId") long categoryId, @RequestParam(name = "productName") String productName, @RequestParam(name = "description") String description,
                             @RequestParam(name = "price") float price, @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "saleId") String saleId, @RequestParam(name = "imageFile") MultipartFile imageFile) {
        productService.persist(categoryId, productName, description, price, quantity, saleId, imageFile);
        return "redirect:/admin/product-list";
    }
  //  @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
    @GetMapping(value = "/product-update")
    public String update(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
        request.setAttribute("product", productService.findById(productId));
        request.setAttribute("sales", saleService.findAll());
        request.setAttribute("categories", categoryService.findAll());
        return "admin/product/updateProduct";
    }

  //  @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
    @PostMapping(value = "/product-update")
    public String update(@RequestParam(value = "newPrice", required = false) Float newPrice, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                         @RequestParam(value = "productId", required = false) Long productId, @RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(value = "oldPrice", required = false) Float oldPrice,
                         @RequestParam(value = "productName", required = false) String productName, @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "quantity", required = false) Integer quantity, @RequestParam(value = "image", required = false) String image,
                         @RequestParam(value = "saleId", required = false) String saleId
    ) {
        productService.merge(newPrice, imageFile, productId, categoryId, oldPrice, productName, description, quantity, image, saleId);
        return "redirect:/admin/product-list";
    }
   // @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    @PostMapping(value = "/product-delete")
    public String delete(HttpServletRequest request) {
        String[] productIds = request.getParameterValues("productId");
        for (String productId : productIds) {
            productService.delete(Long.parseLong(productId));
        }  return "redirect:/admin/product-list";
    }
}
