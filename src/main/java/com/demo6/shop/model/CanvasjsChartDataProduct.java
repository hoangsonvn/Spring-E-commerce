package com.demo6.shop.model;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class CanvasjsChartDataProduct {


      @Autowired
      private ProductDao productDao;

    public Double totalOrderPriceByMonthAndYear(Integer month,Integer year) {
        return productDao.totalOrderPricebyMonthAndYear(month, year);
    }

@Transactional
    public  List<List<Map<Object, Object>>> getCanvasjsDataList(Integer month,Integer year) {

        List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
        List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
        List<StatsDTO> statsDTOList = productDao.listStats(month,year,null,null);
        statsDTOList.forEach(s->{
            Map<Object,Object> map = new HashMap<>();
            map.put("name",s.getName());
            map.put("y",(s.getTotalprice()/(this.totalOrderPriceByMonthAndYear(month,year)- SystemConstant.FEE))*100);
            dataPoints1.add(map);
        });
        list.add(dataPoints1);
        return list;

    }
}