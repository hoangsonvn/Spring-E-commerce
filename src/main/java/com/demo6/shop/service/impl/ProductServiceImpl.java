package com.demo6.shop.service.impl;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.convert.ProductConverter;
import com.demo6.shop.dao.ProductDao;
import com.demo6.shop.dto.CategoryDTO;
import com.demo6.shop.dto.ProductDTO;
import com.demo6.shop.dto.SaleDTO;
import com.demo6.shop.dto.StatsDTO;
import com.demo6.shop.entity.Product;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ICommon iCommon;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductConverter productConverter;


    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productDao.findAll();
        return products.stream().map(s -> productConverter.toDto(s)).collect(Collectors.toList());
    }

    @Override
    public String findOneByProductName(String productName) {
        return productDao.findOneByProductName(productName);
    }

    @Override
    public List<ProductDTO> search(String text, Integer index, Integer pageSize) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productDao.search(text, index, pageSize);
        for (Product product : products) {
            productDTOS.add(productConverter.toDto(product));
        }
        return productDTOS;
    }

    @Override
    public Long countSearch(String text) {
        return productDao.countSearch(text);
    }

    @Override
    public void merge(Float newPrice, MultipartFile imageFile, Long productId, Long categoryId, Float oldPrice, String productName, String description, Integer quantity, String image, String saleId, Date expirationDate) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(saleId);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        Optional<Product> product = Optional.ofNullable(productDao.findById(productId));
        if (product.isPresent()) {
            newPrice = Optional.ofNullable(newPrice).orElse(oldPrice);
            image = imageFile != null && imageFile.getSize() > 0 ? iCommon.image(imageFile) : image;
            ProductDTO productDTO = new ProductDTO(productId, productName, newPrice, quantity, description, image, categoryDTO, saleDTO, expirationDate);
            productDao.update(productConverter.toEntity(productDTO));
        } else {
            throw new NotFoundException("not found");
        }

    }

    @Override
    public void persist(long categoryId, String productName, String description, float price, int quantity, String saleId, MultipartFile imageFile, Date expirationDate) {

        String image = null;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(saleId);
        if (imageFile != null && imageFile.getSize() > 0) {
          //  image = iCommon.image(imageFile);
            image = iCommon.imageUpload(imageFile);
        }
        ProductDTO productDTO = new ProductDTO(productName, price, quantity, description, image, categoryDTO, saleDTO, expirationDate);
        productDao.insert(productConverter.toEntity(productDTO));
    }

    @Override
    public List<Integer> listYears() {

        List<Integer> integerList = productDao.listYears();
        int count = integerList.size();
        List<Integer> newList = new ArrayList<>();
        newList.add(integerList.stream().findFirst().get());
        newList.add(integerList.stream().skip(count - 1).findFirst().get());

        return newList;
    }

    @Override
    public int countStats(Integer month, Integer year) {
        return productDao.coutStats(month, year);
    }

    @Override
    public List<StatsDTO> listStats(Integer month, Integer year, Integer pageIndex, Integer pageSize) {
        return productDao.listStats(month, year, pageIndex, pageSize);
    }

    @Override
    public void update(ProductDTO productDTO) {
        productDao.update(productConverter.toEntity(productDTO));
    }


    @Override
    public void delete(long productId) {
        productDao.delete(productId);
    }

    @Override
    public ProductDTO findById(long productId) {
        Product product = productDao.findById(productId);
       /* SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(product.getSale().getSaleId());
        saleDTO.setSalePercent(product.getSale().getSalePercent());

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(product.getCategory().getCategoryId());
        categoryDTO.setCategoryName(product.getCategory().getCategoryName());

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSaleDTO(saleDTO);
        productDTO.setCategoryDTO(categoryDTO);
*/
        return productConverter.toDto(product);
    }

    @Override
    public List<ProductDTO> findAll(int pageIndex, int pageSize) {
        List<Product> products = productDao.findAll(pageIndex, pageSize);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
          /*  SaleDTO saleDTO = new SaleDTO();

            saleDTO.setSaleId(product.getSale().getSaleId());
            saleDTO.setSalePercent(product.getSale().getSalePercent());

            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setCategoryId(product.getCategory().getCategoryId());
            categoryDTO.setCategoryName(product.getCategory().getCategoryName());

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setImage(product.getImage());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setSaleDTO(saleDTO);
            productDTO.setCategoryDTO(categoryDTO);*/

            productDTOs.add(productConverter.toDto(product));

        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(long categoryId, int pageIndex, int pageSize) {
        List<Product> products = productDao.findAllByCategoryId(categoryId, pageIndex, pageSize);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
    /*        SaleDTO saleDTO = new SaleDTO();

            saleDTO.setSaleId(product.getSale().getSaleId());
            saleDTO.setSalePercent(product.getSale().getSalePercent());

            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setCategoryId(product.getCategory().getCategoryId());
            categoryDTO.setCategoryName(product.getCategory().getCategoryName());

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setImage(product.getImage());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setSaleDTO(saleDTO);
            productDTO.setCategoryDTO(categoryDTO);
*/
            productDTOs.add(productConverter.toDto(product));

        }
        return productDTOs;
    }

    @Override
    public int count() {
        return productDao.count();
    }

    @Override
    public int countByCategoryId(long categoryId) {
        return productDao.countByCategoryId(categoryId);
    }


    @Override
    public List<ProductDTO> search(Long categoryId, String pricing, float priceFrom, float priceTo, String sort,
                                   String text, int pageIndex, int pageSize) {
        List<Product> products = productDao.search(categoryId, pricing, priceFrom, priceTo, sort, text, pageIndex, pageSize);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
    /*        SaleDTO saleDTO = new SaleDTO();

            saleDTO.setSaleId(product.getSale().getSaleId());
            saleDTO.setSalePercent(product.getSale().getSalePercent());

            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setCategoryId(product.getCategory().getCategoryId());
            categoryDTO.setCategoryName(product.getCategory().getCategoryName());

            ProductDTO productDTO = new ProductDTO();

            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setImage(product.getImage());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setSaleDTO(saleDTO);
            productDTO.setCategoryDTO(categoryDTO);
*/
            productDTOs.add(productConverter.toDto(product));

        }
        return productDTOs;
    }

    @Override
    public int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text) {
        return productDao.countBySearch(categoryId, pricing, priceFrom, priceTo, text);
    }

}
