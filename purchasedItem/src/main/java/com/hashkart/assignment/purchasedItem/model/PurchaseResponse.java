package com.hashkart.assignment.purchasedItem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class PurchaseResponse {

    String userId;

    String coupon;

    String totalPrice;

    String discountApplied;

    String totalPayable;

    String paymentMode;

    List<ProductModel> products;
}
