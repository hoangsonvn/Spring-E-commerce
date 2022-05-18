package com.demo6.shop.dao.impl;

import com.demo6.shop.convert.ProductConverter;
import com.demo6.shop.dao.ICartDao;
import com.demo6.shop.entity.Product;
import com.demo6.shop.entity.Sale;
import com.demo6.shop.model.CartDTO;
import com.demo6.shop.model.ProductDTO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class CartDao implements ICartDao {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private SessionFactory sessionFactory;


    public Product findOneById(Long id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);

    }

    public int getSalePercent(Long id) {
        TypedQuery query= sessionFactory.getCurrentSession().createQuery("SELECT u.salePercent FROM Sale u  JOIN u.products p WHERE p.productId= :id ")
                        .setParameter("id",id);

     return (int) query.getSingleResult();

    }

    @Override
    public HashMap<Long, CartDTO> addCart(long id,Integer quantity, HashMap<Long, CartDTO> cart) {
        CartDTO itemCart = new CartDTO();

        ProductDTO product = productConverter.toDto(findOneById(id));//
        if (product != null && cart.containsKey(id)) {
            itemCart = cart.get(id);
          Integer  totalQuantyItem =itemCart.getQuantity() + quantity;
            totalQuantyItem= itemCart.getQuantity() + quantity<6?totalQuantyItem:5;
            /// max =5
            itemCart.setQuantity(totalQuantyItem);
        /*    if(itemCart.getQuantity() + quantity>5){
throw new Nu
            }*/
            double linePrice = itemCart.getProductDTO().getPrice() * itemCart.getQuantity();
            itemCart.setTotalPrice(linePrice);
            itemCart.setTotalPriceSale(linePrice - linePrice*getSalePercent(id)/100);


        } else {
            itemCart.setProductDTO(product);
            itemCart.setQuantity(quantity);
            double totalPrice = product.getPrice() * quantity;
            itemCart.setTotalPrice(totalPrice);
            itemCart.setTotalPriceSale(totalPrice-totalPrice*getSalePercent(id)/100);
        }
        cart.put(id, itemCart);
        return cart;
    }

    @Override
    public HashMap<Long, CartDTO> editCart(long id, int quanty, HashMap<Long, CartDTO> cart) {
        if (cart == null) {
            return cart;
        }
        CartDTO itemCart = new CartDTO();

        if (cart.containsKey(id)) {// tìm xem có id cần hay không
            itemCart = cart.get(id);// lay ra san pham do
            itemCart.setQuantity(quanty);// dat lai so luong
            itemCart.setTotalPrice(quanty * itemCart.getProductDTO().getPrice());// lay gia ra
            itemCart.setTotalPriceSale(quanty*(itemCart.getProductDTO().getPrice()
            -itemCart.getProductDTO().getPrice()*itemCart.getProductDTO().getSaleDTO().getSalePercent()/100));
        }

        cart.put(id, itemCart);
        return cart;
    }

    @Override
    public HashMap<Long, CartDTO> delete(long id, HashMap<Long, CartDTO> cart) {
        if (cart == null) {
            return cart;
        }
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    public int totalQuanty(HashMap<Long, CartDTO> cart) {
        int totalQuanty = 0;
        for (Map.Entry<Long, CartDTO> itemcart : cart.entrySet()) {
             totalQuanty += itemcart.getValue().getQuantity();
        }
        return totalQuanty;
    }

    public float totalPrice(HashMap<Long, CartDTO> cart) {
        double totalPrice = 0;
        for (Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
            totalPrice += itemCart.getValue().getTotalPriceSale();
        }
        return (float) totalPrice;
    }




}
