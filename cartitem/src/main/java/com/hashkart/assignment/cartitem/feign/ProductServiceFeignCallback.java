package com.hashkart.assignment.cartitem.feign;

import com.hashkart.assignment.cartitem.model.ProductModel;
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
        return new ProductModel("DummyName",0L,0.0f,0.0f,0,0);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return Collections.singletonList( new ProductModel("DummyName",0L,0.0f,0.0f,0,0));
    }
}
