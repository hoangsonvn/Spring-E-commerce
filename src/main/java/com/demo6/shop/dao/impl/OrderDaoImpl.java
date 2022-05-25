package com.demo6.shop.dao.impl;

import com.demo6.shop.controller.admin.PermissionController;
import com.demo6.shop.dao.ItemDao;
import com.demo6.shop.dao.OrderDao;
import com.demo6.shop.entity.Order;
import com.demo6.shop.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ItemDao itemDao;
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Override
    public void deleteByUserId(Long id) {
        String sql = "SELECT order_user.order_id FROM order_user WHERE order_user.user_id= :id";
        NativeQuery nativeQuery = sessionFactory.getCurrentSession().createNativeQuery(sql)
                .setParameter("id", id);
        List<BigInteger> ids = nativeQuery.getResultList();
        for (BigInteger idn : ids) {
          delete(idn.longValue());
      }

    }

    @Override
    public Double totalPriceByCurrentMonth() {
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(
                "select sum(o.priceTotal) from Order o where" +
                        " month(o.buyDate)=month(current_date()) " +
                        "and " +
                        "year(o.buyDate)=year(current_date())");
        return (Double) typedQuery.getSingleResult();
    }

    @Override
    public Double totalPrice() {
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery("SELECT SUM(o.priceTotal) FROM Order o");
        return (Double) typedQuery.getSingleResult();
    }

    @Override
    public void insert(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().merge(order);
    }



    @Override
    public void delete(long orderId) {
        try {
            itemDao.delete(orderId);
            String sql = "delete FROM springeco.order_user where order_user.order_id= :orderId";
            sessionFactory.getCurrentSession().createNativeQuery(sql).setParameter("orderId", orderId).executeUpdate();
        } catch (NullPointerException e) {
            logger.error("khong co don hang");
        }
    }

    @Override
    public List<Order> findAll(int pageIndex, int pageSize) {
        String sql = "SELECT o FROM Order o ORDER BY o.orderId desc ";
        int first = pageIndex * pageSize;
        TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(sql, Order.class).setFirstResult(first).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Order> findByBuyer(long userId) {
        String sql = "SELECT o FROM Order o WHERE o.buyer.userId = :id ORDER BY o.orderId DESC";
        TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(sql, Order.class)
                .setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(o) FROM Order o";
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
        long count = (long) typedQuery.getSingleResult();
        return (int) count;
    }

    @Override
    public Order findById(long orderId) {
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

}
