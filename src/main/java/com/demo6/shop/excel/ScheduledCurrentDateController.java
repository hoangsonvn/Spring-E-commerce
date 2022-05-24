package com.demo6.shop.excel;

import com.demo6.shop.dto.ScheduleDTO;
import com.demo6.shop.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Component
public class ScheduledCurrentDateController {
    @Autowired
    private ScheduleService scheduleService;

    @org.springframework.scheduling.annotation.Scheduled(cron = " */10 * * * * * ")
    public void scheduleFixedDelayTask() {
        List<ScheduleDTO> scheduleDTOS = scheduleService.findAll();
        for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            Date expirationDate = scheduleDTO.getExpirationDate();
            Date newDate = new java.sql.Date(new java.util.Date().getTime());
           if(newDate.compareTo(expirationDate)>0){
               //th neu xoa san pham do roi
           }
        }


    }


    @GetMapping("/admin/currentdate")
    public void exportToExcel(HttpServletResponse response
    ) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stats" + currentDateTime + " .xlsx";
        response.setHeader(headerKey, headerValue);
        List<ScheduleDTO> scheduleDTOS = scheduleService.findAll();
        CurrentDateExcelExpoter excelExporter = new CurrentDateExcelExpoter(scheduleDTOS);
        excelExporter.export(response);
    }

}