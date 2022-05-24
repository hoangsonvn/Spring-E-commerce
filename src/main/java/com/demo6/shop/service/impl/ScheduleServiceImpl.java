package com.demo6.shop.service.impl;

import com.demo6.shop.dao.ScheduleDao;
import com.demo6.shop.dto.ScheduleDTO;
import com.demo6.shop.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
@Autowired
private ScheduleDao scheduleDao;
    @Override
    public List<ScheduleDTO> findAll() {
        return scheduleDao.findAll();
    }
}
