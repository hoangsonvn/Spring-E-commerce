package com.demo6.shop.common;

import org.springframework.web.multipart.MultipartFile;

public interface ICommon {
     String image(MultipartFile imageFile);
     int totalPage(int count,int pageSize);
}
