package com.demo6.shop.service.impl;

import com.demo6.shop.dao.ICartDao;
import com.demo6.shop.dto.CartDTO;
import com.demo6.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartDao cartDao ;


    @Override
    public HashMap<Long, CartDTO> addCart(long id,Integer quantity, HashMap<Long, CartDTO> cart) {
        return cartDao.addCart(id,quantity,cart);

    }

    @Override
    public HashMap<Long, CartDTO> editCart(long id, int quanty, HashMap<Long, CartDTO> cart) {
        return cartDao.editCart(id,quanty,cart);
    }

    @Override
    public HashMap<Long, CartDTO> Delete(long id, HashMap<Long, CartDTO> cart) {
        return cartDao.delete(id,cart);
    }

    @Override
    public int totalQuanty(HashMap<Long, CartDTO> cart) {
        return cartDao.totalQuanty(cart);
    }

    @Override
    public float totalPrice(HashMap<Long, CartDTO> cart) {
        return cartDao.totalPrice(cart);
    }


}

