package com.hashkart.assignment.cartitem.feign;

import com.hashkart.assignment.cartitem.model.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="products-service", fallback = ProductServiceFeignCallback.class)
public interface ProductServiceFeign {

    @GetMapping("/product/{id}")
    public ProductModel getProduct(@PathVariable Long id);

    @GetMapping("/product/")
    public List<ProductModel> getAllProducts();
}
