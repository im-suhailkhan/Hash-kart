package com.hashkart.assignment.purchasedItem.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class CartItemModel {

    Long productId;

    long id;

    String quantityAdded;

    public CartItemModel(Long productId, long id, String quantityAdded) {
        this.productId = productId;
        this.id = id;
        this.quantityAdded = quantityAdded;
    }
}
