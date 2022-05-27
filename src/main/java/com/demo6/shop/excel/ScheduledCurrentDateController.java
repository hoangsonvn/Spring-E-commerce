package com.demo6.shop.excel;

import com.demo6.shop.dto.ScheduleDTO;
import com.demo6.shop.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
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
    private static final Logger logger = LoggerFactory.getLogger(ScheduledCurrentDateController.class);

    @GetMapping("/admin/currentdate")
    public String exportToExcel(HttpServletResponse response, HttpServletRequest request
    ) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stats" + currentDateTime + " .xlsx";
        response.setHeader(headerKey, headerValue);
        List<ScheduleDTO> scheduleDTOS;
        scheduleDTOS = scheduleService.findAll();
        CurrentDateExcelExpoter excelExporter = new CurrentDateExcelExpoter(scheduleDTOS);
        excelExporter.export(response);
        return "redirect:" + request.getHeader("Referer");
    }

}