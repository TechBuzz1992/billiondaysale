package com.peaasecommerce.billiondaysale.exception;

import com.peaasecommerce.billiondaysale.model.Product;

public class ProductNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "Product not found";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(Product product) {
        super(String.format("Product named '%s' is not found", product.getName()));
    }
}
