package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.ItemDao;
import com.demo6.shop.entity.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Item item) {
		sessionFactory.getCurrentSession().save(item);
	}
	@Override
	public List<Item> findByOrderId(long orderId) {
		String sql = "SELECT i FROM Item i WHERE i.order.orderId =: orderId";
		TypedQuery<Item> query = sessionFactory.getCurrentSession().createQuery(sql,Item.class)
				.setParameter("orderId",orderId);
		return query.getResultList();
	}
}
