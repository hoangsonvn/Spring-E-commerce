package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.SaleDao;
import com.demo6.shop.entity.Sale;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SaleDaoImpl implements SaleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Sale> findAll() {
		String sql = "SELECT s FROM Sale s";
		TypedQuery<Sale> typedQuery = sessionFactory.getCurrentSession().createQuery(sql,Sale.class);
		return typedQuery.getResultList();
	}

}
