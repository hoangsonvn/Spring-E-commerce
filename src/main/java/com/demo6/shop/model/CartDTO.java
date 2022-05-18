package com.demo6.shop.model;

public class CartDTO {

    private long cartId;
    private UserDTO userDTO;
    private ProductDTO productDTO;
    private int quantity;
    private double totalPrice;
    private double totalPriceSale;
    private OrderDTO orderDTO;

    public CartDTO(long cartId, UserDTO userDTO, ProductDTO productDTO, int quantity, double totalPrice, double totalPriceSale, OrderDTO orderDTO) {
        this.cartId = cartId;
        this.userDTO = userDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalPriceSale = totalPriceSale;
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public CartDTO(long cartId, UserDTO userDTO, ProductDTO productDTO, int quantity, double totalPrice, double totalPriceSale) {
        this.cartId = cartId;
        this.userDTO = userDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalPriceSale = totalPriceSale;
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
        // TODO Auto-generated constructor stub
    }

    public CartDTO(long cartId, UserDTO userDTO, ProductDTO productDTO, int quantity) {
        super();
        this.cartId = cartId;
        this.userDTO = userDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
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
