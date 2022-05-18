package com.demo6.shop.service.impl;

import com.demo6.shop.model.ProductDTO;
import com.demo6.shop.service.LikeService;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private ProductService productService;

    @Override
    public HashMap<Long,ProductDTO> insert(Long id, HashMap<Long, ProductDTO> likeMap) {
        ProductDTO productDTO = productService.findById(id);
        likeMap.put(id, productDTO);
        return likeMap;
    }

    @Override
    public void delete(Long id, HashMap<Long, ProductDTO> likeMap) {
        likeMap.remove(id);
    }
}
