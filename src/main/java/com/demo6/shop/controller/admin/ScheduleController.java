package com.demo6.shop.controller.admin;

import com.demo6.shop.dto.ProductDTO;
import com.demo6.shop.service.ProductService;
import com.demo6.shop.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
@Component
public class ScheduleController {
    @Autowired
    private ProductService productService;

    @Scheduled(cron = " 0 0 1 * * ? ")
    public void scheduleFixedDelayTask() {
        Date newDate = new java.sql.Date(new java.util.Date().getTime());
        List<ProductDTO> productDTOS = productService.findAll();
        productDTOS.stream().filter(s -> newDate.compareTo(s.getExpirationDate()) > 0)
                .forEach(s -> {
                    s.setQuantity(0);
                    productService.update(s);
                });
    }
}
