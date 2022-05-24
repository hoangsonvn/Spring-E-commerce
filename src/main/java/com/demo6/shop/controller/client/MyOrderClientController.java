package com.demo6.shop.controller.client;


import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.dto.ItemDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.service.ItemService;
import com.demo6.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/client")
public class MyOrderClientController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/my-order")
    public String myOrder(HttpServletRequest request) {
        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        long userId = userPrincipal.getUserId();
        request.setAttribute("orders", orderService.findByBuyer(userId));
        return "client/my_order";
    }

    @GetMapping(value = "order-details")
    public String orderDetails(HttpServletRequest request, @RequestParam(name = "orderId") long orderId) {
        List<ItemDTO> itemDTOs = itemService.findByOrderId(orderId);
        float subTotal = 0;
        for (ItemDTO itemDTO : itemDTOs) {
            subTotal += ((itemDTO.getProductDTO().getPrice() - itemDTO.getProductDTO().getPrice() * itemDTO.getProductDTO().getSaleDTO().getSalePercent() / 100)
                    * itemDTO.getQuantity());
        }

        double grandTotal = subTotal + SystemConstant.FEE;
        request.setAttribute("subTotal", subTotal);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("items", itemDTOs);
        return "client/order_details";
    }


}
