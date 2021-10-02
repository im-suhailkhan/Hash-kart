package com.hashkart.assignment.cartitem.repository;

import com.hashkart.assignment.cartitem.model.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel,Long> {

    List<CartItemModel> findAllById(Long id);
}
