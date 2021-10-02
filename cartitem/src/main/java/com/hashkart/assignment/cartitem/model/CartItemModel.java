package com.hashkart.assignment.cartitem.model;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class CartItemModel  {

    @Id
    @Column(name = "productId")
    Long productId;


    @Column(name = "id")
    long id;

    @Column(name = "quantityAdded")
    String quantityAdded;


    @Transient
    ProductModel product;
}
