package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.CategoryDao;
import com.demo6.shop.entity.Category;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		String sql = "SELECT COUNT(c) FROM Category c";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Category findOne(Long id) {
		return sessionFactory.getCurrentSession().find(Category.class,id);
	}

	@Override
	public List<Category> findAll() {
		String sql = "SELECT c FROM Category c";
		TypedQuery<Category> typedQuery = sessionFactory.getCurrentSession().createQuery(sql,Category.class);
		return typedQuery.getResultList();
	}

	@Override
	public void persist(Category category) {
		sessionFactory.getCurrentSession().persist(category);
	}

	@Override
	public Category update(Category category) {
		return (Category) sessionFactory.getCurrentSession().merge(category);
	}

	@Override
	public void delete(Long id) {
		Category category= sessionFactory.getCurrentSession().find(Category.class,id);
		sessionFactory.getCurrentSession().delete(category);
	}

}
