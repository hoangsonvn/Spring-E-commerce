package com.demo6.shop.controller.client;


import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.controller.admin.PermissionController;
import com.demo6.shop.dto.ItemDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.service.ItemService;
import com.demo6.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;

import static com.mysql.cj.conf.PropertyKey.logger;

@Controller
@RequestMapping(value = "/client")
public class MyOrderClientController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @GetMapping(value = "/my-order")
    public String myOrder(HttpServletRequest request) {
        String message = request.getParameter("message");
        if(message != null){
            request.setAttribute("message",resourceBundle.getString(message));
        }
        try {
            UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
            long userId = userPrincipal.getUserId();
            request.setAttribute("orders", orderService.findByBuyer(userId));
        }
      catch (NullPointerException e){
            logger.error("not found user");
      }
        return "client/my_order";
    }

    @GetMapping(value = "order-details")
    public String orderDetails(HttpServletRequest request, @RequestParam(name = "orderId") long orderId) {
      try{
        List<ItemDTO> itemDTOs = itemService.findByOrderId(orderId);
        float subTotal = 0;
        for (ItemDTO itemDTO : itemDTOs) {
            subTotal += ((itemDTO.getProductDTO().getPrice() - itemDTO.getProductDTO().getPrice() * itemDTO.getProductDTO().getSaleDTO().getSalePercent() / 100)
                    * itemDTO.getQuantity());
            double grandTotal = subTotal + SystemConstant.FEE;
            request.setAttribute("subTotal", subTotal);
            request.setAttribute("grandTotal", grandTotal);
            request.setAttribute("items", itemDTOs);
        }}catch(EntityNotFoundException e){
          logger.error("chi tiet san pham da removed"+e);
          return "redirect:/client/my-order?message=detailsRemoved";
      }
      return "client/order_details";
    }
}
