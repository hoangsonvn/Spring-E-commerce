package com.demo6.shop.controller.admin;

import com.demo6.shop.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;
@Controller
public class CanvasjsChartController {
    @Autowired
    private CanvasjsChartService canvasjsChartService;

    @PreAuthorize("hasAnyAuthority('STATS_READ')")
    @GetMapping("/admin/canvasjschart")
    public String springMVC(ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        return "admin/chart/chart";
    }



}