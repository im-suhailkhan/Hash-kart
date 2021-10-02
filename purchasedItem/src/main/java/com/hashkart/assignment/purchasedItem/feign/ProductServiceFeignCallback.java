package com.hashkart.assignment.purchasedItem.feign;


import com.hashkart.assignment.purchasedItem.model.ProductModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceFeignCallback implements ProductServiceFeign{

    public ProductServiceFeignCallback() {
    }

    @Override
    public ProductModel getProduct(Long id) {
        return new ProductModel("Dummy",3L,4.2f,4.2f,3,0);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return Collections.singletonList(new ProductModel("Dummy",3L,4.2f,4.2f,3,0));
    }

    @Override
    public void updateProductItem(Long productId, int quantityAdded) {

    }
}
