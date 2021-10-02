package com.hashkart.assignment.purchasedItem.model;


public class ProductModel {

    String productName;

    Long productId;

    float price;

    float ratings;

    int quantityAvailable;

    int quantityAdded;

    public ProductModel(String productName, Long productId, float price, float ratings, int quantityAvailable, int quantityAdded) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.ratings = ratings;
        this.quantityAvailable = quantityAvailable;
        this.quantityAdded = quantityAdded;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public int getQuantityAdded() {
        return quantityAdded;
    }

    public void setQuantityAdded(int quantityAdded) {
        this.quantityAdded = quantityAdded;
    }
}
