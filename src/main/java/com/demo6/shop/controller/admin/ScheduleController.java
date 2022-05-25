package com.demo6.shop.controller.admin;

import com.demo6.shop.dto.ProductDTO;
import com.demo6.shop.dto.ScheduleDTO;
import com.demo6.shop.service.ProductService;
import com.demo6.shop.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@Component
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ProductService productService;

  //  @Scheduled(cron = " */10 * * * * * ")
    @GetMapping("/test")
    public void scheduleFixedDelayTask() {
      /*  List<ScheduleDTO> scheduleDTOS = scheduleService.findAll();
        for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            Date expirationDate = scheduleDTO.getExpirationDate();
            Date newDate = new java.sql.Date(new java.util.Date().getTime());
            if(expirationDate == null)
            if (newDate.compareTo(expirationDate) > 0) {
                //th neu xoa san pham do roi
            }
        }*/
       Date newDate = new java.sql.Date(new java.util.Date().getTime());
        List<ProductDTO> productDTOS = productService.findAll();
        productDTOS.stream().filter(s-> newDate.compareTo(s.getExpirationDate())>0)
                .forEach(s->{s.setQuantity(0);
                productService.update(s);});

       /* Date newDate = new java.sql.Date(new java.util.Date().getTime());
        List<ProductDTO> productDTOS = productService.findAll();
       for(ProductDTO productDTO :productDTOS){
          if( newDate.compareTo(productDTO.getExpirationDate())>0){
              productDTO.setQuantity(0);
              productService.update(productDTO);
          }
       }*/


}}
