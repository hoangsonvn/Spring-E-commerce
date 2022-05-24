package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.dto.StatsDTO;
import com.demo6.shop.service.CanvasjsChartProductService;
import com.demo6.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class StatsController {
    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);
    @Autowired
    private ICommon iCommon;
    @Autowired
    private ProductService productService;
    @Autowired
    private CanvasjsChartProductService canvasjsChartProductService;

    @GetMapping("admin/statistical")
    public String stats(@RequestParam(value = "month", required = false) Integer month,
                        @RequestParam(value = "year", required = false) Integer year,
                        @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                        HttpServletRequest request) {

        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = productService.countStats(month, year);
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);

        Integer first = null, last = null;
        try {
            List<Integer> integerList = productService.listYears();
            first = integerList.get(0);
            last = integerList.get(1);
        } catch (NoSuchElementException e) {
            logger.error("" + e);
        }
        /*Integer first, last;
        first = integerList.get(0);
        last = integerList.get(1);
*/
        List<StatsDTO> statsDTOList = new ArrayList<>();
        try {
            statsDTOList = productService.listStats(month, year, pageIndex, SystemConstant.PAGESIZE);
            request.setAttribute("dataPointsList", canvasjsChartProductService.getCanvasjsChartData(month, year));
        } catch (NoSuchElementException e) {
            logger.error("" + e);
        }
        logger.info("vẽ ra biểu đồ");
        request.setAttribute("month", month);
        request.setAttribute("year", year);
        request.setAttribute("first", first);
        request.setAttribute("last", last);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("stats", statsDTOList);
        logger.info("DS thống kê");
        return "admin/chart/statistical";
    }

}
