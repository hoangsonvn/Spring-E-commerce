package com.demo6.shop.dao.impl;


import com.demo6.shop.dao.CanvasjsChartProductDao;
import com.demo6.shop.model.CanvasjsChartDataProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartDaoProductImpl implements CanvasjsChartProductDao {

@Autowired
private CanvasjsChartDataProduct canvasjsChartDataProduct;
    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartProductData(Integer month,Integer year) {
        return canvasjsChartDataProduct.getCanvasjsDataList(month,year);

    }

    @Override
    public Double total(Integer month,Integer year) {
        return canvasjsChartDataProduct.totalOrderPriceByMonthAndYear(month,year);
    }
}