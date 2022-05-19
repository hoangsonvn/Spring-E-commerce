package com.demo6.shop.excel;


import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.model.StatsDTO;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private ProductService productService;


    @GetMapping("admin/export")
    public void exportToExcel(HttpServletResponse response,
                              @RequestParam(value = "month", required = false) Integer month,
                              @RequestParam(value = "year", required = false) Integer year,
                              @RequestParam(value = "pageIndex",required = false) Integer pageIndex) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Stats_" + currentDateTime +"------"+month+" / "+year+ ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<StatsDTO> listStats = productService.listStats(month, year, pageIndex, null);

        UserExcelExporter excelExporter = new UserExcelExporter(listStats);

        excelExporter.export(response);
    }

}