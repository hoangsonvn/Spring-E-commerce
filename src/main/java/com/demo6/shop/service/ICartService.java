package com.demo6.shop.service;

import com.demo6.shop.dto.CartDTO;

import java.util.HashMap;

public interface ICartService {
    public HashMap<Long, CartDTO> addCart(long id, Integer quantity,HashMap<Long,CartDTO> cart);

    public HashMap<Long, CartDTO> editCart(long id,int quanty, HashMap<Long,CartDTO> cart);

    public HashMap<Long, CartDTO> Delete(long id, HashMap<Long,CartDTO> cart);
    public int totalQuanty(HashMap<Long, CartDTO> cart) ;
    public float totalPrice(HashMap<Long, CartDTO> cart) ;
}
