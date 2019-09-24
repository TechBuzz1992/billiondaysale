package com.peaasecommerce.billiondaysale.service;

import com.peaasecommerce.billiondaysale.exception.NotEnoughProductsInStockException;
import com.peaasecommerce.billiondaysale.exception.ProductNotFoundException;
import com.peaasecommerce.billiondaysale.exception.ProductPurchaseLimitExceededException;
import com.peaasecommerce.billiondaysale.model.Product;
import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    String addProduct(Product product) throws ProductPurchaseLimitExceededException;

    String removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
