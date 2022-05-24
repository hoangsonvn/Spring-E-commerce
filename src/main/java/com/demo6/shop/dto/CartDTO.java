package com.demo6.shop.dto;

public class CartDTO {

    private long cartId;
    private UserDTO userDTO;
    private ProductDTO productDTO;
    private int quantity;
    private double totalPrice;
    private double totalPriceSale;
    private OrderDTO orderDTO;
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public double getTotalPriceSale() {
        return totalPriceSale;
    }

    public void setTotalPriceSale(double totalPriceSale) {
        this.totalPriceSale = totalPriceSale;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public CartDTO() {
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
