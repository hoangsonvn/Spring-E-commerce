package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.ScheduleDao;
import com.demo6.shop.dto.ScheduleDTO;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleDaoImpl implements ScheduleDao {
    private static Logger logger = LoggerFactory.getLogger(ScheduleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<ScheduleDTO> findAll() {
        String sql = "select item.productName, product.quantity 'so luong con lai',product.expired_date , sum(item.quantity)'tong sp ban dc' ,sum(unit_price) as 'tong_gia ban ra', sum(unit_price)/sum(item.quantity)\n" +
                "as 'gia tb' from item  left join product on product.product_id=item.product_id \n" +
                "inner join order_user on item.order_id=order_user.order_id where day(buy_date) = day(curdate())  group by product.product_id\n" +
                "UNION \n" +
                "select p.product_name ,p.quantity,p.expired_date,null,null,p.price from product as p \n" +
                " where p.product_name not in (select item.productName from item);";
        NativeQuery typedQuery = sessionFactory.getCurrentSession().createNativeQuery(sql);
        List<Object[]> objects = typedQuery.getResultList();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Object[] object : objects) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setName((String) object[0]);
            scheduleDTO.setRemainingAmount((Integer) object[1]);
            scheduleDTO.setExpirationDate((Date) object[2]);
            scheduleDTO.setQuantity((BigDecimal) object[3]);
            scheduleDTO.setTotaPrice((Double) object[4]);
            scheduleDTO.setPrice((Double) object[5]);
            if (scheduleDTO.getExpirationDate()==null){
                scheduleDTO.setStatus(false);
            }else{
                scheduleDTO.setStatus(true);
            }
            scheduleDTOS.add(scheduleDTO);
            logger.debug("{}, {},{},{},{},{}", object[0], object[1], object[2], object[3], object[4], object[5]);
        }
        return scheduleDTOS;
    }
}
