package com.hashkart.assignment.purchasedItem.feign;

import com.hashkart.assignment.purchasedItem.model.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="product-service", fallback = ProductServiceFeignCallback.class)
public interface ProductServiceFeign {

    @GetMapping("/products/{id}")
    ProductModel getProduct(@PathVariable Long id);

    @GetMapping("/products/")
    List<ProductModel> getAllProducts();

    @PostMapping("/products/{id}")
    void updateProductItem(@PathVariable Long id,@RequestBody int quantityAdded);
}
