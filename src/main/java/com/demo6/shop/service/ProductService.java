package com.demo6.shop.service;

import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.dto.ProductDTO;
import com.demo6.shop.dto.StatsDTO;
import com.demo6.shop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static com.demo6.shop.constant.SystemConstant.PAGESIZE;

public interface ProductService {
    List<ProductDTO> search(String text, Integer index,Integer pageSIze);
    Long countSearch(String text);
    void merge(Float newPrice, MultipartFile imageFile, Long productId, Long categoryId, Float oldPrice, String productName, String description, Integer quantity, String image, String saleId, Date expirationDate);

    void persist(long categoryId, String productName, String description, float price, int quantity, String saleId, MultipartFile multipartFile, Date expirationDate);

    List<Integer> listYears();

    int countStats(Integer month, Integer year);

    List<StatsDTO> listStats(Integer month, Integer year, Integer pageIndex, Integer pageSize);

    void update(ProductDTO productDTO);

    void delete(long productId);

    ProductDTO findById(long productId);

    List<ProductDTO> findAll(int pageIndex, int pageSize);

    List<ProductDTO> findAllByCategoryId(long categoryId, int pageIndex, int pagesize);

    int count();

    int countByCategoryId(long categoryId);

    List<ProductDTO> search(Long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex, int pageSize);

    int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text);
}
