package com.demo6.shop.dao.impl;


import com.demo6.shop.dto.CanvasjsChartData;
import com.demo6.shop.dao.CanvasjsChartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {
@Autowired
private CanvasjsChartData canvasjsChartData;
    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return canvasjsChartData.total();
    }

}