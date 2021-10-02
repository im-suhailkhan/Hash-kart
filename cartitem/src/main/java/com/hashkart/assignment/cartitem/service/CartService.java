package com.hashkart.assignment.cartitem.service;

import com.google.gson.Gson;
import com.hashkart.assignment.cartitem.feign.ProductServiceFeign;
import com.hashkart.assignment.cartitem.model.CartItemModel;
import com.hashkart.assignment.cartitem.model.CartResponseModel;
import com.hashkart.assignment.cartitem.model.ProductModel;
import com.hashkart.assignment.cartitem.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    private ProductServiceFeign productServiceFeign;

    public CartResponseModel viewCartItems() {
        List<CartItemModel> cartItemModels = cartItemRepository.findAllById(3L);
        List<CartItemModel> cartItemModels1 = new ArrayList<>();
        for (CartItemModel c : cartItemModels) {
            ProductModel productModel =  productServiceFeign.getProduct(c.getProductId());
            c.setProduct(productModel);
            cartItemModels1.add(c);
            log.info(new Gson().toJson(productModel));
//            c.setProduct(productModel);
            break;
        }
        CartResponseModel cartResponseModel = new CartResponseModel(cartItemModels1,3);
        return cartResponseModel;
    }

    public CartItemModel saveCartItems(CartItemModel cartItemModel){
      return   cartItemRepository.save(cartItemModel);
    }

    public void delete(CartItemModel cartItemModel) {
         cartItemRepository.delete(cartItemModel);
    }
}
