package com.demo6.shop.dao;

import com.demo6.shop.dto.CartDTO;

import java.util.HashMap;

public interface ICartDao {
     HashMap<Long, CartDTO> addCart(long id,Integer quantity, HashMap<Long,CartDTO> cart);

     HashMap<Long, CartDTO> editCart(long id,int quanty, HashMap<Long,CartDTO> cart);

     HashMap<Long, CartDTO> delete(long id, HashMap<Long,CartDTO> cart);
     int totalQuanty(HashMap<Long, CartDTO> cart) ;
     float totalPrice(HashMap<Long, CartDTO> cart) ;
}
