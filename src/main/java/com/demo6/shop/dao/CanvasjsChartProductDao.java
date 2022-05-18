package com.demo6.shop.dao;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartProductDao {
    Double total(Integer month,Integer year) ;

        List<List<Map<Object, Object>>> getCanvasjsChartProductData(Integer month,Integer year);

}
