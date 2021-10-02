package com.hashkart.assignment.product.service;

import com.hashkart.assignment.product.model.Product;
import com.hashkart.assignment.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean isProductExist(Long productId) {
        return productRepository.findByProductId(productId) != null;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findByProductId(id);
    }

    public List<Product> findAllPriceDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    public List<Product> findAllPriceAsc() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    public List<Product> findAllRatingsAsc() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "ratings"));
    }

    public List<Product> findAllRatingsDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "ratings"));
    }

    public List<Product> findByProductPrice(String price) {
        return productRepository.findByPrice(price);
    }

    public List<Product> findByProductRatings(float rating) {
        return productRepository.findByRatings(rating);
    }
}
