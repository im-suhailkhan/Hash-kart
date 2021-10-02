package com.hashkart.assignment.cartitem.controller;

import com.hashkart.assignment.cartitem.model.CartItemModel;
import com.hashkart.assignment.cartitem.model.CartResponseModel;
import com.hashkart.assignment.cartitem.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartService cartItemsService;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public  ResponseEntity<CartResponseModel> viewCart() {
        try {
            CartResponseModel cartItemsModel = cartItemsService.viewCartItems();
            return new ResponseEntity<>(cartItemsModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<CartItemModel> addToCartAPI(@RequestBody CartItemModel cartItemModel) {

        try {
             CartItemModel cartItemModel1 = cartItemsService.saveCartItems(cartItemModel);
            return new ResponseEntity<>(cartItemModel1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/")
    public ResponseEntity<Object> removeCart(@RequestBody CartItemModel cartItemModel) {
        try {
             cartItemsService.delete(cartItemModel);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
