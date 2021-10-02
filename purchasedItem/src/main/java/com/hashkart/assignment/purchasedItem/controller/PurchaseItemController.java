package com.hashkart.assignment.purchasedItem.controller;

import com.google.gson.Gson;
import com.hashkart.assignment.purchasedItem.model.*;
import com.hashkart.assignment.purchasedItem.service.PurchaseItemService;
import com.hashkart.assignment.purchasedItem.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PurchaseItemController {

    @Autowired
    PurchaseItemService purchaseItemService;

    @PostMapping("/checkout")
    public ResponseEntity<Object> postCheckOutAPI(@RequestBody PurchaseRequest purchaseRequest) {
        try {
            if(purchaseRequest != null && CommonUtil.isValidString(purchaseRequest.getCoupon())){
                PurchaseResponse purchaseResponse = new PurchaseResponse();
                float totalAmount = 0.0f;
                int totalQuantity = 0;
                for (ProductModel p : purchaseRequest.getProducts()) {
                  totalAmount +=  p.getPrice() * p.getQuantityAdded();
                  totalQuantity += p.getQuantityAdded();
                  purchaseItemService.updateProductItem(p.getProductId(),p.getQuantityAdded());
                }
                purchaseResponse.setUserId(purchaseRequest.getUserId());
                purchaseResponse.setProducts(purchaseRequest.getProducts());
                purchaseResponse.setTotalPrice(totalAmount +"");
                if(CommonUtil.COUPON_CODE.equalsIgnoreCase(purchaseRequest.getCoupon())){
                    purchaseResponse.setDiscountApplied("12");
                    purchaseResponse.setCoupon(purchaseRequest.getCoupon());
                    purchaseResponse.setTotalPayable(totalAmount - ((12 *totalAmount)/100) +"");
                }else  if(totalQuantity > 10){
                    purchaseResponse.setDiscountApplied("6");
                    purchaseResponse.setTotalPayable(totalAmount - ((6* totalAmount)/100 ) +"");
                }else  purchaseResponse.setTotalPayable(totalAmount +"");
                purchaseResponse.setPaymentMode(purchaseRequest.getPaymentMode());
                purchaseItemService.save(new PurchaseItemModel(purchaseResponse));
                return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Product Already Exist", HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/applycoupon")
    public ResponseEntity<Object> postApplyCouponAPI(@RequestBody PurchaseRequest purchaseRequest) {
        try {
            if(purchaseRequest != null && CommonUtil.isValidString(purchaseRequest.getCoupon())){
                PurchaseResponse purchaseResponse = new PurchaseResponse();
                if(CommonUtil.COUPON_CODE.equalsIgnoreCase(purchaseRequest.getCoupon())){
                    float totalAmount = 0.0f;
                    for (ProductModel p : purchaseRequest.getProducts()) {
                        totalAmount +=  p.getPrice() * p.getQuantityAdded();
                    }
                    purchaseResponse.setProducts(purchaseRequest.getProducts());
                    purchaseResponse.setTotalPrice(totalAmount +"");
                    purchaseResponse.setDiscountApplied("12");
                    purchaseResponse.setCoupon(purchaseRequest.getCoupon());
                    purchaseResponse.setTotalPayable(totalAmount - ((12* totalAmount) /100 ) +"");
                }else return new ResponseEntity<>("Invalid Coupon", HttpStatus.OK);
              return new ResponseEntity<>(new Gson().toJson(purchaseResponse), HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Invalid Coupon", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/paymentdetails/{id}")
    public ResponseEntity<Object> getPaymentDetails(@PathVariable(name = "id") String id) {
        try {
            log.info("paymentdetais request");
            List<PurchaseItemModel> purchaseItemModels = purchaseItemService.getPaymentDetailsById(id);
            log.info(new Gson().toJson(purchaseItemModels));
            return   new ResponseEntity<>(purchaseItemModels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
