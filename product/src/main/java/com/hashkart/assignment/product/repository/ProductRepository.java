package com.hashkart.assignment.product.repository;

import com.hashkart.assignment.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductId(Long id);

    List<Product> findByPrice(String price);

    List<Product> findByRatings(float rating);
}
