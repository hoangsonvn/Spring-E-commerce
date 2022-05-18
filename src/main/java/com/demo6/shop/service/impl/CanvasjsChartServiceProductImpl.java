package com.demo6.shop.service.impl;

import java.util.List;
import java.util.Map;

import com.demo6.shop.dao.CanvasjsChartProductDao;
import com.demo6.shop.service.CanvasjsChartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanvasjsChartServiceProductImpl implements CanvasjsChartProductService {

    @Autowired
    private CanvasjsChartProductDao canvasjsChartProductDao;

    public void setCanvasjsChartDao(CanvasjsChartProductDao canvasjsChartProductDao) {
        this.canvasjsChartProductDao = canvasjsChartProductDao;
    }

    @Override
    public Double total(Integer month, Integer year) {
        return canvasjsChartProductDao.total(month,year);
    }

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData(Integer month,Integer year) {
        return canvasjsChartProductDao.getCanvasjsChartProductData(month,year);
    }

}