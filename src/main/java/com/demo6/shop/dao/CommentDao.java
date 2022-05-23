package com.demo6.shop.dao;

import com.demo6.shop.entity.Comment;

import java.util.List;

public interface CommentDao {
    void persist(Comment comment);
    List<Comment> findAllByProductId(Long id);
    void delete(Long id);
}
