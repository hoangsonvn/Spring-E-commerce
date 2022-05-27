package com.demo6.shop.service.impl;

import com.demo6.shop.dao.CanvasjsCurrentChartDao;
import com.demo6.shop.service.CanvasjsChartCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CanvasjsChartCurrentServiceImpl implements CanvasjsChartCurrentService {

    @Autowired
    private CanvasjsCurrentChartDao canvasjsCurrentChartDao;


    @Override
    public List<List<Map<Object, Object>>> getCanvasjsCurrentChartData() {
        return canvasjsCurrentChartDao.getCanvasjsCurrentChartData();
    }

}