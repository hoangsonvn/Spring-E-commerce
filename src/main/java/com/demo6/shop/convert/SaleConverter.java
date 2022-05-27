package com.demo6.shop.convert;

import com.demo6.shop.dto.SaleDTO;
import com.demo6.shop.entity.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleConverter {
    public SaleDTO toDTO(Sale sale){
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setSalePercent(sale.getSalePercent());
        return saleDTO;
    }
}
