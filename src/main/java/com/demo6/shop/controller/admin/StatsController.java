package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.model.StatsDTO;
import com.demo6.shop.service.CanvasjsChartProductService;
import com.demo6.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class StatsController {
    private static Logger logger = LoggerFactory.getLogger(StatsController.class);
@Autowired
   private ICommon iCommon;
    @Autowired
    private ProductService productService;
    @Autowired
    private CanvasjsChartProductService canvasjsChartProductService;

    @GetMapping("admin/statistical")
    public String stats(@RequestParam(value = "month", required = false) Integer month,
                        @RequestParam(value = "year", required = false) Integer year,
                        @RequestParam(value = "pageIndex",required = false) Integer pageIndex,
                        HttpServletRequest request) {

        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.countStats(month, year);
        Integer totalPage = iCommon.totalPage(count,SystemConstant.PAGESIZE);

        List<Integer> integerList = productService.listYears();
        Integer first, last;
        first = integerList.get(0);
        last = integerList.get(1);

        List<StatsDTO> statsDTOList = productService.listStats(month, year, pageIndex, SystemConstant.PAGESIZE);
        request.setAttribute("dataPointsList", canvasjsChartProductService.getCanvasjsChartData(month, year));
        logger.info("vẽ ra biểu đồ");
        request.setAttribute("first", first);
        request.setAttribute("last", last);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("stats", statsDTOList);
        logger.info("ds thống kê");
        return "admin/chart/statistical";
    }

}
