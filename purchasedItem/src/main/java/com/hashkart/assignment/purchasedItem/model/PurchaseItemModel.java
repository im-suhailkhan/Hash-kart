package com.hashkart.assignment.purchasedItem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "purchase")
@Getter
@Setter
public class PurchaseItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "productIds")
    @ElementCollection
    List<Long> productIds;

    @Column(name = "userId")
    String userId;

    @Column(name = "coupon")
    String coupon;

    @Column(name = "totalPrice")
    String totalPrice;

    @Column(name = "discountApplied")
    @Value("${cart.discount.percentage=15}")
    String discountApplied;

    @Column(name = "totalPayable")
    String totalPayable;

    @Column(name = "paymentMode")
    String paymentMode;

    public PurchaseItemModel(PurchaseResponse purchaseResponse) {
        this.productIds = purchaseResponse.getProducts().stream().map(ProductModel::getProductId).collect(Collectors.toList());
        this.setCoupon(purchaseResponse.getCoupon());
        this.setUserId(purchaseResponse.getUserId());
        this.setTotalPrice(purchaseResponse.getTotalPrice());
        this.setDiscountApplied(purchaseResponse.getDiscountApplied());
        this.setTotalPayable(purchaseResponse.getTotalPayable());
        this.setPaymentMode(purchaseResponse.getPaymentMode());
    }

    public PurchaseItemModel() {
    }
}
