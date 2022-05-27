package com.demo6.shop.excel;


import com.demo6.shop.dto.StatsDTO;
import com.demo6.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ExcelController {

    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @GetMapping("admin/export")
    public String exportToExcel(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam(value = "month", required = false) Integer month,
                                @RequestParam(value = "year", required = false) Integer year,
                                @RequestParam(value = "pageIndex", required = false) Integer pageIndex) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stats_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<StatsDTO> listStats = null;
        try {
            listStats = productService.listStats(month, year, pageIndex, null);
        } catch (EntityNotFoundException e) {
            logger.error("no product");
            return "redirect:admin/statistical?message=No products found";
        }
        UserExcelExporter excelExporter = new UserExcelExporter(listStats);
        excelExporter.export(response);
        return "redirect:" + request.getHeader("Referer");
    }

}