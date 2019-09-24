package com.peaasecommerce.billiondaysale.service.implementation;

import com.peaasecommerce.billiondaysale.exception.NotEnoughProductsInStockException;
import com.peaasecommerce.billiondaysale.exception.ProductNotFoundException;
import com.peaasecommerce.billiondaysale.exception.ProductPurchaseLimitExceededException;
import com.peaasecommerce.billiondaysale.model.Product;
import com.peaasecommerce.billiondaysale.model.QuantityType;
import com.peaasecommerce.billiondaysale.repository.ProductRepository;
import com.peaasecommerce.billiondaysale.service.ProductService;
import com.peaasecommerce.billiondaysale.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, ProductService productService) {

        this.productRepository = productRepository;
        this.productService = productService;
    }


    @Override
    public String addProduct(Product product) throws ProductPurchaseLimitExceededException {

        if (products.containsKey(product) && product.getQuantityType().equals(QuantityType.UNLIMITED.toString())) {
            products.replace(product, products.get(product) + 1);
        } else if (products.containsKey(product) && product.getQuantityType().equals(QuantityType.LIMITED.toString())) {
            throw new ProductPurchaseLimitExceededException(product);
        } else {
            products.put(product, 1);
        }

        return String.format("Product named '%s' is successfully added into the cart", product.getName());
    }

    @Override
    public String removeProduct(Product product) {

        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }

        return String.format("Product named '%s' is successfully removed from the cart.", product.getName());
    }


    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            product = productRepository.getOne(entry.getKey().getId());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
        productRepository.saveAll(products.keySet());
        productRepository.flush();
        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
