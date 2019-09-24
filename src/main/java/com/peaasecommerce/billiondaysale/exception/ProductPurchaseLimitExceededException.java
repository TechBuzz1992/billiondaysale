package com.peaasecommerce.billiondaysale.exception;

import com.peaasecommerce.billiondaysale.model.Product;

public class ProductPurchaseLimitExceededException extends Exception {

    private static final String DEFAULT_MESSAGE = "Cannot purchase more than one product";

    public ProductPurchaseLimitExceededException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductPurchaseLimitExceededException(Product product) {
        super(String.format("%s named as '%s' ", DEFAULT_MESSAGE, product.getName()));
    }
}
