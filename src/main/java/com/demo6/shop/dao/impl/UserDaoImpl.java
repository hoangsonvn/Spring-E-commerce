package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.UserDao;
import com.demo6.shop.entity.User;
import com.graphbuilder.org.apache.harmony.awt.gl.Crossing;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void delete(long userId) {
		User user = findById(userId);
		sessionFactory.getCurrentSession().delete(user);
	}
	@Override
	public User findById(long userId) {
		return sessionFactory.getCurrentSession().find(User.class, userId);
	}
	@Override
	public List<User> findAll(int pageIndex, int pageSize) {
		int first = pageIndex * pageSize;
		String sql = "SELECT u FROM User u";
		TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery(sql,User.class).setFirstResult(first).setMaxResults(pageSize);
		return typedQuery.getResultList();
	}

	@Override
	public User findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
		String sql = "SELECT u FROM User u WHERE (u.email = :account or u.phone = :account) and u.password = :password";
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql,User.class)
				.setParameter("account",account)
				.setParameter("password",password);
		return  query.getSingleResult();
	}

	@Override
	public User loadUserByUsername(String account) {
		String sql = "SELECT u FROM User u WHERE u.email =: email and u.verify = true";
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(sql,User.class)
				.setParameter("email",account);
		return  query.getSingleResult();
	}
	@Override
	public int count() {
		String sql = "SELECT count(u) FROM User u";
		TypedQuery typedQuery = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) typedQuery.getSingleResult();
		return (int) count;
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT u FROM User u WHERE u.email = :email";
		Query query = sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("email",email);
		return (User) query.uniqueResult();
	}

}
