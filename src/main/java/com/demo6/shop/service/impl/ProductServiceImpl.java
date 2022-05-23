package com.demo6.shop.service.impl;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.convert.ProductConverter;
import com.demo6.shop.dao.ProductDao;
import com.demo6.shop.entity.Product;
import com.demo6.shop.model.CategoryDTO;
import com.demo6.shop.model.ProductDTO;
import com.demo6.shop.model.SaleDTO;
import com.demo6.shop.model.StatsDTO;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void merge(Float newPrice, MultipartFile imageFile, Long productId, Long categoryId, Float oldPrice, String productName, String description, Integer quantity, String image, String saleId) {


        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(saleId);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        Optional<Product> product = Optional.ofNullable(productDao.findById(productId));
        if (product.isPresent()) {
      /*  ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productId);
        productDTO.setSaleDTO(saleDTO);
        productDTO.setCategoryDTO(categoryDTO);
        productDTO.setProductName(productName);
        productDTO.setDescription(description);
        productDTO.setQuantity(quantity);*/
            newPrice = Optional.ofNullable(newPrice).orElse(oldPrice);
            //     newPrice = newPrice == null || newPrice.equals("") ? oldPrice:newPrice;

            //      newPrice = StringUtils.isNotBlank(newPrice) ? oldPrice:newPrice;

            //productDTO.setPrice(newPrice);
     /*   if (newPrice == null || newPrice.equals("")) {
            productDTO.setPrice(oldPrice);
        } else {
            productDTO.setPrice(Float.parseFloat(newPrice));
        }*/
            image = imageFile != null && imageFile.getSize() > 0 ? iCommon.image(imageFile) : image;
            //productDTO.setImage(image);

            ProductDTO productDTO = new ProductDTO(productId, productName, newPrice, quantity, description, image, categoryDTO, saleDTO);
            productDao.update(productConverter.toEntity(productDTO));
        } else {
            throw new NotFoundException("not found");
        }

    }

    @Override
    public void persist(long categoryId, String productName, String description, float price, int quantity, String saleId, MultipartFile imageFile) {
   /*     Permission permissionCreate = new Permission();
        permissionCreate.setPermissionName(create);
        Permission permissionUpdate = new Permission();

        Permission permissionDelete = new Permission();*/
       /* List<Permission> permissionList = new ArrayList<>();

        permissionList.stream().filter(s -> s.getPermissionKey() != null).collect(Collectors.toList());
*/
        String image = null;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(categoryId);
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(saleId);
        if (imageFile != null && imageFile.getSize() > 0) {
            image = iCommon.image(imageFile);
        }
        ProductDTO productDTO = new ProductDTO(productName, price, quantity, description, image, categoryDTO, saleDTO);
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

    /*@Override
    public void insert(ProductDTO productDTO) {
        Product product = new Product();
        Category category = new Category();
        category.setCategoryId(productDTO.getCategoryDTO().getCategoryId());
        Sale sale = new Sale();
        sale.setSaleId(productDTO.getSaleDTO().getSaleId());
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setSale(sale);
        product.setCategory(category);

        productDao.insert(product);
    }
*/
   /* @Override
    public void update(ProductDTO productDTO) {
        Product product = new Product();
        Category category = new Category();
        category.setCategoryId(productDTO.getCategoryDTO().getCategoryId());
        Sale sale = new Sale();
        sale.setSaleId(productDTO.getSaleDTO().getSaleId());
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setSale(sale);
        product.setCategory(category);

        productDao.update(product);
    }
*/
    @Override
    public void delete(long productId) {
        productDao.delete(productId);
    }

    @Override
    public ProductDTO findById(long productId) {
        Product product = productDao.findById(productId);
        SaleDTO saleDTO = new SaleDTO();
        product.getCategory().getCategoryName();
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

        return productDTO;
    }

    @Override
    public List<ProductDTO> findAll(int pageIndex, int pageSize) {
        List<Product> products = productDao.findAll(pageIndex, pageSize);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            SaleDTO saleDTO = new SaleDTO();

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

            productDTOs.add(productDTO);

        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(long categoryId, int pageIndex, int pageSize) {
        List<Product> products = productDao.findAllByCategoryId(categoryId, pageIndex, pageSize);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            SaleDTO saleDTO = new SaleDTO();

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

            productDTOs.add(productDTO);

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
            SaleDTO saleDTO = new SaleDTO();

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

            productDTOs.add(productDTO);

        }
        return productDTOs;
    }

    @Override
    public int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text) {
        return productDao.countBySearch(categoryId, pricing, priceFrom, priceTo, text);
    }

}
