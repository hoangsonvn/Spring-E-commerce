package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.PermissionDao;
import com.demo6.shop.entity.Permission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class PermissionDaoImpl implements PermissionDao {
    @Autowired
    private SessionFactory sessionFactory;



    public Permission findOneById(Long id) {
        String sql = "SELECT p FROM Permission p WHERE p.id= :id";
        TypedQuery<Permission> typedQuery = sessionFactory.getCurrentSession().createQuery(sql, Permission.class)
                .setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Permission> findAll() {
        String sql = "SELECT p FROM Permission p";
        TypedQuery<Permission> typedQuery = sessionFactory.getCurrentSession().createQuery(sql, Permission.class);
        return typedQuery.getResultList();
    }
}

