package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.RoleDao;
import com.demo6.shop.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Role role) {
        sessionFactory.getCurrentSession().merge(role);
    }

    @Override
    public Role findOne(Long id) {
        return sessionFactory.getCurrentSession().find(Role.class, id);
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT r FROM Role r";
        TypedQuery<Role> typedQuery = sessionFactory.getCurrentSession().createQuery(sql, Role.class);
        return typedQuery.getResultList();
    }

    @Override
    public void delete(Long id) {
        Optional<Role> roleOptional = Optional.ofNullable(findOne(id));
        roleOptional.ifPresent(s -> sessionFactory.getCurrentSession().delete(s));
    }
}
