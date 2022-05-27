package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.ItemService;
import com.demo6.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.ResourceBundle;
//@PreAuthorize("hasAnyAuthority('ORDER_READ','ORDER_DELETE')")

@Controller
@RequestMapping(value = "/admin")
public class OrderManagementAdminController {
    @Autowired
    private ICommon iCommon;
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping(value = "/order-list")
    public String findAll(HttpServletRequest request, @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        String message = request.getParameter("message");
        String deletemessage = request.getParameter("deletemessage");
        String tick = request.getParameter("tick");
        if (message != null) {
            request.setAttribute("message", resourceBundle.getString(message));
        }
        if (deletemessage != null) {
            request.setAttribute("deletemessage", resourceBundle.getString(deletemessage));
        } if(tick != null ){
            request.setAttribute("tick",resourceBundle.getString(tick));
        }
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = orderService.count();
        int totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("orders", orderService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/order/order_list";
    }
    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping(value = "order-updateHome")
    public String orderUpdateHome(@RequestParam(value = "orderId") long orderId) {
        orderService.updateHome(orderId);
        return "redirect:/admin/home";
    }

    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping(value = "order-update")
    public String orderUpdate(HttpServletRequest request, @RequestParam(value = "orderId") long orderId) {
        orderService.updateHome(orderId);
        return "redirect:" + request.getHeader("Referer");
    }


    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping(value = "order-details")
    public String orderDetails(HttpServletRequest request, @RequestParam(value = "orderId") long orderId) {
        try {
            request.setAttribute("items", itemService.findByOrderId(orderId));
            request.setAttribute("order", orderService.findById(orderId));
        } catch (EntityNotFoundException e) {
            return "redirect:/admin/order-list?message=detailsRemoved";
        }
        return "admin/order/order_details";
    }

    //@ExceptionHandler(ObjectNotFoundException.class)
    @PreAuthorize("hasAuthority('ORDER_DELETE')")
    @PostMapping("/order-delete")
    public String orderDelete(HttpServletRequest request) {
        String[] orderIds = request.getParameterValues("orderId");
        if(orderIds == null){
            return "redirect:/admin/order-list?tick=tick";
        }
        for (String orderId : orderIds) {
            orderService.delete(Long.parseLong(orderId));
        }
        return "redirect:/admin/order-list?deletemessage=removedOrder";

    }
}
