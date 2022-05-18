package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.CategoryDao;
import com.demo6.shop.entity.Category;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Category findOne(Long id) {
		return sessionFactory.getCurrentSession().find(Category.class,id);
	}

	@Override
	public List<Category> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
		return criteria.list();
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
