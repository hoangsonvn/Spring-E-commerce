package com.demo6.shop.dao;

import com.demo6.shop.model.CartDTO;

import java.util.HashMap;

public interface ICartDao {
    public HashMap<Long, CartDTO> addCart(long id,Integer quantity, HashMap<Long,CartDTO> cart);

    public HashMap<Long, CartDTO> editCart(long id,int quanty, HashMap<Long,CartDTO> cart);

    public HashMap<Long, CartDTO> delete(long id, HashMap<Long,CartDTO> cart);
    public int totalQuanty(HashMap<Long, CartDTO> cart) ;
    public float totalPrice(HashMap<Long, CartDTO> cart) ;
}