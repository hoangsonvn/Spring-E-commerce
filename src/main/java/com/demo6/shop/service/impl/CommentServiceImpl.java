package com.demo6.shop.service.impl;

import com.demo6.shop.dao.CommentDao;
import com.demo6.shop.entity.Comment;
import com.demo6.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public void persist(Comment comment) {
        commentDao.persist(comment);
    }

    @Override
    public List<Comment> findAllByProductId(Long id) {
        return commentDao.findAllByProductId(id);
    }

    @Override
    public void delete(Long id) {
        commentDao.delete(id);
    }
}
