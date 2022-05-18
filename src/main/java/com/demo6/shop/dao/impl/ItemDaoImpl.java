package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.ItemDao;
import com.demo6.shop.entity.Item;
import org.hibernate.Query;
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
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findByOrderId(long orderId) {
		String sql = "SELECT i FROM Item i WHERE i.order.orderId =: orderId";
		TypedQuery<Item> query = sessionFactory.getCurrentSession().createQuery(sql,Item.class)
				.setParameter("orderId",orderId);
		return query.getResultList();
	}

}
