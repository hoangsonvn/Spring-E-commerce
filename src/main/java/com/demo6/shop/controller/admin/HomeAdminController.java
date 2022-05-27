package com.demo6.shop.controller.admin;

import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value = "/admin")
public class HomeAdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CanvasjsChartCurrentService canvasjsChartCurrentService;

    private static final Logger logger = LoggerFactory.getLogger(HomeAdminController.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @GetMapping(value = "/home")
    public String home(HttpServletRequest request) {
        Double totalPrice = orderService.totalPrice() == null ? 0 : orderService.totalPrice();
        Double totalPriceByCurrentMonth = orderService.totalPriceByCurrentMonth() == null ? 0 : orderService.totalPriceByCurrentMonth();
        String messagefile = request.getParameter("imagefile");
        String messageformat = request.getParameter("imageformat");
        if (messagefile != null) {
            request.setAttribute("imagefile", resourceBundle.getString(messagefile));
        }
        if (messageformat != null) {
            request.setAttribute("imageformat", resourceBundle.getString(messageformat));
        }
        logger.info("file to lagrge");
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartCurrentService.getCanvasjsCurrentChartData();

        request.setAttribute("dataPointsList", canvasjsDataList);
        request.setAttribute("orders", orderService.findAll(0, SystemConstant.PAGESIZE));
        request.setAttribute("countorder", orderService.count());
        request.setAttribute("coutproduct", productService.count());
        request.setAttribute("count", userService.count());
        request.setAttribute("countcategory", categoryService.count());
        request.setAttribute("totalprice", totalPrice);
        request.setAttribute("totalpricebycurrentmonth", totalPriceByCurrentMonth);
        return "admin/home";
    }


}
