package com.hashkart.assignment.cartitem.model;

import java.util.List;

public class CartResponseModel {

    List<CartItemModel> cartItems;
    long userId;

    public CartResponseModel(List<CartItemModel> cartItemModels, long userId) {
        this.cartItems = cartItemModels;
        this.userId = userId;
    }

    public List<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
