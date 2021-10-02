package com.hashkart.assignment.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    String productName;
    @Id
    @Column(name = "productId")
    Long productId;

    @Column(name = "price")
    float price;

    @Column(name = "ratings")
    float ratings;

    @Column(name = "quantityAvailable")
    int quantityAvailable;

    @Column(name = "quantityAdded")
    int quantityAdded;


}
