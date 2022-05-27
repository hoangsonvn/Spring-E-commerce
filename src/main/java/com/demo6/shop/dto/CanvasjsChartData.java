package com.demo6.shop.dto;


import com.demo6.shop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CanvasjsChartData {
    @Autowired
    private ProductDao productDao;

    public List<List<Map<Object, Object>>> total() {
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        List<StatsByYearDTO> statsByYearDTOS = productDao.totalEachYear();
        // lấy ra year và tổng giá mỗi năm
        statsByYearDTOS.forEach(s -> {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("x", s.getYear());
            hashMap.put("y", s.getTotal());
            dataPoints1.add(hashMap);
        });

        list.add(dataPoints1);
        return list;
    }


}