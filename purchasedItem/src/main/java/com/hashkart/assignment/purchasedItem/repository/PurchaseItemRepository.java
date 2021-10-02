package com.hashkart.assignment.purchasedItem.repository;

import com.hashkart.assignment.purchasedItem.model.PurchaseItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemModel, String> {

    List<PurchaseItemModel> findAllByUserId(String id);
}
