package com.demo6.shop.service;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartProductService {
    Double total(Integer month, Integer year);
    List<List<Map<Object, Object>>> getCanvasjsChartData(Integer month, Integer year);
}
