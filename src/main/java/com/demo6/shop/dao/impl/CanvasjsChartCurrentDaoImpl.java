package com.demo6.shop.dao.impl;


import com.demo6.shop.dao.CanvasjsCurrentChartDao;
import com.demo6.shop.dto.CanvasjsChartCurrentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartCurrentDaoImpl implements CanvasjsCurrentChartDao {
    @Autowired
   private CanvasjsChartCurrentData canvasjsChartCurrentData;

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsCurrentChartData() {
        return canvasjsChartCurrentData.getCanvasjsCurrentDataList();
    }

}