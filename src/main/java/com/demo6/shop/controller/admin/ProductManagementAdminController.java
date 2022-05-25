package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.CategoryService;
import com.demo6.shop.service.ProductService;
import com.demo6.shop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @GetMapping(value = "/product-list")
    public String findAll(HttpServletRequest request, @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.count();
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        iCommon.notificate(request);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("products", productService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/product/listProduct";
    }


    @GetMapping(value = "/product-list-by-category")
    public String findAllByCategory(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId,
                                    @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        String tick = request.getParameter("tick");
        if (tick != null) {
            request.setAttribute("tick", resourceBundle.getString(tick));
        }
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
        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", resourceBundle.getString(message));
        }
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("sales", saleService.findAll());
        return "admin/product/createNewProduct";
    }


    //  @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    @ExceptionHandler(NoResultException.class)
    @PostMapping(value = "/product-create")
    public String insertPost(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId, @RequestParam(name = "productName") String productName, @RequestParam(name = "description") String description,
                             @RequestParam(name = "price") float price, @RequestParam(name = "quantity") int quantity,
                             @RequestParam(name = "saleId") String saleId, @RequestParam(name = "imageFile") MultipartFile imageFile, @RequestParam(name = "expirationDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expirationDate) {

        Optional<String> optionalS = Optional.ofNullable(productService.findOneByProductName(productName));
        if (optionalS.isPresent()) {
            return "redirect:/admin/product-create?message=duplicate";
        }
        productService.persist(categoryId, productName, description, price, quantity, saleId, imageFile, expirationDate);
        return "redirect:/admin/product-list?messagecreate=createProduct";
    }


    //  @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
    @GetMapping(value = "/product-update")
    public String update(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
        String message = request.getParameter("message");
        if (message != null) {
            request.setAttribute("message", resourceBundle.getString(message));
        }
        request.setAttribute("product", productService.findById(productId));
        request.setAttribute("sales", saleService.findAll());
        request.setAttribute("categories", categoryService.findAll());
        return "admin/product/updateProduct";
    }


    //  @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE')")
    @PostMapping(value = "/product-update")
    public String update(HttpServletRequest request, @RequestParam(value = "newPrice", required = false) Float newPrice, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                         @RequestParam(value = "productId", required = false) Long productId, @RequestParam(value = "categoryId", required = false) Long categoryId, @RequestParam(value = "oldPrice", required = false) Float oldPrice,
                         @RequestParam(value = "productName", required = false) String productName, @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "quantity", required = false) Integer quantity, @RequestParam(value = "image", required = false) String image,
                         @RequestParam(value = "saleId", required = false) String saleId, @RequestParam(name = "expirationDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expirationDate) {
        Optional<String> optionalS = Optional.ofNullable(productService.findOneByProductName(productName));
        String name = productService.findById(productId).getProductName();
        if (optionalS.isPresent() && !optionalS.get().equals(name)) {
            return "redirect:/admin/product-update?productId="+productId+"&message=duplicate";
        }
        productService.merge(newPrice, imageFile, productId, categoryId, oldPrice, productName, description, quantity, image, saleId, expirationDate);
        return "redirect:/admin/product-list?messageupdate=updateProduct";
    }


    // @PreAuthorize("hasAnyAuthority('ADMIN_DELETE')")
    @PostMapping(value = "/product-delete")
    public String delete(HttpServletRequest request) {
        String[] productIds = request.getParameterValues("productId");
        if (productIds == null) {
            return "redirect:/admin/product-list?tick=tick";
        }
        for (String productId : productIds) {
            productService.delete(Long.parseLong(productId));
        }
        return "redirect:/admin/product-list?messagedelete=deleteProduct";
    }
}
