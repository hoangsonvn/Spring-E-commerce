package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.PermissionDao;
import com.demo6.shop.entity.Permission;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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

    @Override
    public List<Permission> findByRoleId(Long roleId) {
        String nativeSql="select p.id,p.permissionKey,p.permissionName,p.description_new from permission p join role_permission rp on rp.permission_id=p.id where role_id= :roleId";
        NativeQuery<Permission> nativeQuery = sessionFactory.getCurrentSession().createNativeQuery(nativeSql,Permission.class)
                .setParameter("roleId",roleId);
        return nativeQuery.getResultList();
    }

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

