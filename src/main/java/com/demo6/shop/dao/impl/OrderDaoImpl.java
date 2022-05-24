package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.OrderDao;
import com.demo6.shop.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

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
        Order order = sessionFactory.getCurrentSession().find(Order.class, orderId);
        sessionFactory.getCurrentSession().remove(order);
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
        TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery(sql,Order.class)
                .setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(o) FROM Order o";
        TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
        long count= (long) typedQuery.getSingleResult();
        return (int) count;
    }
    @Override
    public Order findById(long orderId) {
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

}
