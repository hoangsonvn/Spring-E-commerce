package com.demo6.shop.dao.impl;

import com.demo6.shop.dao.CommentDao;
import com.demo6.shop.entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void persist(Comment comment) {
        sessionFactory.getCurrentSession().persist(comment);
    }

    @Override
    public List<Comment> findAllByProductId(Long id) {
        String sql = "SELECT c FROM Comment c WHERE c.product.productId=:id";
        TypedQuery<Comment> typedQuery = sessionFactory.getCurrentSession().createQuery(sql,Comment.class)
                .setParameter("id",id);
        return typedQuery.getResultList();
    }

    @Override
    public void delete(Long id) {
        Comment comment = sessionFactory.getCurrentSession().find(Comment.class,id);
        sessionFactory.getCurrentSession().delete(comment);
    }
}
