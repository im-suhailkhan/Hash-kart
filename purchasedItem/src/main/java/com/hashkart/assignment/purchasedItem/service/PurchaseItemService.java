package com.hashkart.assignment.purchasedItem.service;

import com.hashkart.assignment.purchasedItem.feign.ProductServiceFeign;
import com.hashkart.assignment.purchasedItem.model.PurchaseItemModel;
import com.hashkart.assignment.purchasedItem.model.PurchaseRequest;
import com.hashkart.assignment.purchasedItem.repository.PurchaseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseItemService {

    @Autowired
    PurchaseItemRepository purchaseItemRepository;

    @Autowired
    private ProductServiceFeign productServiceFeign;

    public void save(PurchaseItemModel purchaseItemModel) {
        purchaseItemRepository.save(purchaseItemModel);
    }

    public void updateProductItem(Long productId, int quantityAdded) {
        productServiceFeign.updateProductItem(productId,quantityAdded);
    }

    public List<PurchaseItemModel> getPaymentDetailsById(String id) {
        return purchaseItemRepository.findAllByUserId(id);
    }
}
