package com.demo6.shop.service;

import com.demo6.shop.dto.ProductDTO;

import java.util.HashMap;

public interface LikeService {
     HashMap<Long,ProductDTO> insert(Long id,HashMap<Long , ProductDTO > likeMap);
     void delete(Long id,HashMap<Long,ProductDTO> likeMap);

}
