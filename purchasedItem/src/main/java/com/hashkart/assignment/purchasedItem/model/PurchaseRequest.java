package com.hashkart.assignment.purchasedItem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseRequest {
    List<ProductModel> products;
    String coupon;
    String paymentMode;
    String userId;
}
