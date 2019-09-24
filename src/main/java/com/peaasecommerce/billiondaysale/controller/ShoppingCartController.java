package com.peaasecommerce.billiondaysale.controller;

import com.peaasecommerce.billiondaysale.exception.ProductNotFoundException;
import com.peaasecommerce.billiondaysale.exception.ProductPurchaseLimitExceededException;
import com.peaasecommerce.billiondaysale.model.Product;
import com.peaasecommerce.billiondaysale.service.ProductService;
import com.peaasecommerce.billiondaysale.service.ShoppingCartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    private ResponseEntity responseEntity;

    private HttpStatus httpStatus;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping(path = "/shoppingCart", produces = "application/json")
    public ResponseEntity shoppingCartProducts() {

        JSONObject resultJson = new JSONObject();
        JSONObject productJson = new JSONObject();
        JSONObject productOneJson = new JSONObject();
        JSONObject totalJson = new JSONObject();
        Map<Product,Integer> productMap = shoppingCartService.getProductsInCart();
        Set<Product> productSet = productMap.keySet();
        //List<Product> productList = new ArrayList<>();
        Iterator it = productSet.iterator();

        while(it.hasNext()){
              Product product = (Product) it.next();

              productOneJson.clear();
              productOneJson.put("id",product.getId());
              productOneJson.put("name",product.getName());
              productOneJson.put("description",product.getDescription());
              productOneJson.put("price",product.getPrice());
              productOneJson.put("quantity_added",productMap.get(product));

              productJson.put("Item_"+i,new JSONObject(productOneJson));

              i++;
        }

        resultJson.put("products",productJson);
        resultJson.put("total",shoppingCartService.getTotal().toString());
        resultJson.put("status",HttpStatus.ACCEPTED);
        responseEntity = new ResponseEntity(resultJson.toJSONString(), HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping(path = "/shoppingCart/addProduct/{productId}", produces = "application/json")
    public ResponseEntity addProductToCart(@PathVariable("productId") Long productId) throws ProductPurchaseLimitExceededException {

        JSONObject resultJson = new JSONObject();
        AtomicReference<String> addMessage = new AtomicReference<>("");
        if (productService.findById(productId).isPresent()) {
            Product product = productService.findById(productId).get();
            try {
                addMessage.set(shoppingCartService.addProduct(product));
                httpStatus = HttpStatus.ACCEPTED;
            } catch (ProductPurchaseLimitExceededException e) {
                addMessage.set(e.getMessage());
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
            }
        } else {
            addMessage.set("Product not found with Id " + productId);
            httpStatus = HttpStatus.NOT_FOUND;
        }

        resultJson.put("status",httpStatus);
        resultJson.put("message",addMessage);
        responseEntity = new ResponseEntity(resultJson, httpStatus);
        return responseEntity;

        //return addMessage.get();
    }

    @GetMapping(path = "/shoppingCart/removeProduct/{productId}", produces = "application/json")
    public ResponseEntity removeProductFromCart(@PathVariable("productId") Long productId) {

        JSONObject resultJson = new JSONObject();

        AtomicReference<String> removeMessage = new AtomicReference<>("");
        if (productService.findById(productId).isPresent()) {

            Product product = productService.findById(productId).get();
            removeMessage.set(shoppingCartService.removeProduct(product));
            httpStatus = HttpStatus.ACCEPTED;

        } else {
            removeMessage.set("Product not found with Id " + productId);
            httpStatus = HttpStatus.NOT_FOUND;
        }

        resultJson.put("status",httpStatus);
        resultJson.put("message",removeMessage);
        responseEntity = new ResponseEntity(resultJson, httpStatus);
        return responseEntity;
        //return removeMessage.get();
    }

}
