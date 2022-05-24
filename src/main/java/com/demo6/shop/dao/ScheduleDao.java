package com.demo6.shop.dao;

import com.demo6.shop.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleDao {
    List<ScheduleDTO> findAll();
}
