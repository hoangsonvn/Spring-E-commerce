package com.demo6.shop.dto;

import com.demo6.shop.dao.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CanvasjsChartCurrentData {


    @Autowired
    private ScheduleDao scheduleDao;

    public List<List<Map<Object, Object>>> getCanvasjsCurrentDataList() {
        List<ScheduleDTO> scheduleDTOS = scheduleDao.findAll();
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            Double TotalCurrentPrice = scheduleDTO.getTotaPrice() == null ? 0 : scheduleDTO.getTotaPrice();map = new HashMap<Object, Object>();
            map.put("label", scheduleDTO.getName());map.put("y", TotalCurrentPrice);dataPoints1.add(map);
        }

        list.add(dataPoints1);
        return list;
    }
}