package com.hashkart.assignment.product.controller;

import com.google.gson.Gson;
import com.hashkart.assignment.product.model.Product;
import com.hashkart.assignment.product.service.ProductService;
import com.hashkart.assignment.product.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Object> postProductAPI(@RequestBody Product product) {
        try {
            if(product != null && product.getProductId() > 0 && CommonUtil.isValidString(product.getProductName())){
                if(!productService.isProductExist(product.getProductId())){
                    Product product1 =  productService.save(product);
                    return new ResponseEntity<>(new Gson().toJson(product1), HttpStatus.OK);
                }else  return new ResponseEntity<>("Product Already Exist", HttpStatus.METHOD_NOT_ALLOWED);
            }else {
                return new ResponseEntity<>("Product Already Exist", HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bulkUpload")
    public ResponseEntity<Object> bulkUpload(@RequestBody List<Product> productList) {
        try {
            List<Product> duplicateEntry = new ArrayList<>();
            List<Product> invalidEntry = new ArrayList<>();
            for (Product product : productList) {
                if(product.getProductId() > 0 && CommonUtil.isValidString(product.getProductName())){
                    if(!productService.isProductExist(product.getProductId())){
                         productService.save(product);
                    }else  duplicateEntry.add(product);
                }else {
                    invalidEntry.add(product);
                }
            }
            StringBuilder message = new StringBuilder("Total successfully uploaded " + (productList.size() - duplicateEntry.size()) + "\n" + "Total failure " + duplicateEntry.size() + " \n  ");
            if(duplicateEntry.size() != 0){
                for (Product p : duplicateEntry) {
                    message.append(p.getProductId()).append(" ");
                }
            }
          return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product")
    public ResponseEntity<Object> updateProductAPI(@RequestBody Product product) {
        try {
            if(product != null && product.getProductId() > 0 &&  CommonUtil.isValidString(product.getProductName())){
                if(productService.isProductExist(product.getProductId())){

                    return new ResponseEntity<>(product, HttpStatus.OK);
                }else   return new ResponseEntity<>("Product doesn't exist", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("check request body is not proper", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getAllProductByParam(@RequestParam (name = "price",required = false) String price,@RequestParam (name = "ratings",required = false) String ratings) {
        try {
            List<Product> productList = new ArrayList<>();
            if(price != null){
                productList = productService.findByProductPrice(price);
            }else {
                if(ratings != null){
                    float rating = Float.parseFloat(ratings);
                    productList = productService.findByProductRatings(rating);
                }else {
                    productList = productService.getAllProducts();
                }
            }
            log.info(new Gson().toJson(productList));
           return new ResponseEntity<>(new Gson().toJson(productList), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/products")
    public ResponseEntity<Object> getAllProductSortBy(@RequestParam (name = "sortby") String sortby) {
        try {
            List<Product> productList = new ArrayList<>();
            if(sortby != null){
                String[] sorts = sortby.split("[.]");
                log.info(sortby +"\n"+sorts);
                switch (sorts[0]){
                    case "price":
                        if(sorts.length == 2 && sorts[1].equalsIgnoreCase("desc")){
                            productList =  productService.findAllPriceDesc();
                        }else  productList =  productService.findAllPriceAsc();
                        break;
                    case "ratings":
                        if(sorts.length == 2 && sorts[1].equalsIgnoreCase("desc")){
                          productList = productService.findAllRatingsDesc();
                        }else  productList = productService.findAllRatingsAsc();
                        break;
                }
            }else {
                productList = productService.getAllProducts();
            }
            log.info(new Gson().toJson(productList));
            return new ResponseEntity<>(new Gson().toJson(productList), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    @GetMapping("/product/{id}")
//    public ResponseEntity<Object> getAllProduct(@PathVariable (value ="id") Long id) {
//        try {
//           return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable (value ="id") Long id) {
        try {
           return productService.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping("/products/{id}")
    public void updateProductItem(@PathVariable Long id,@RequestBody int quantityAdded){
        try {
            Product product = productService.getProductById(id);
            product.setQuantityAvailable(product.getQuantityAvailable() - quantityAdded);
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
