package com.demo6.shop.service.impl;

import com.demo6.shop.convert.SaleConverter;
import com.demo6.shop.dao.SaleDao;
import com.demo6.shop.dto.SaleDTO;
import com.demo6.shop.entity.Sale;
import com.demo6.shop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleConverter saleConverter;
    @Autowired
    private SaleDao saleDao;

    @Override
    public List<SaleDTO> findAll() {
        List<Sale> sales = saleDao.findAll();
        List<SaleDTO> saleDTOs = new ArrayList<>();
        for (Sale sale : sales) {
            saleDTOs.add(saleConverter.toDTO(sale));
        }
        return saleDTOs;
    }

}
