package com.peaasecommerce.billiondaysale.service.implementation;

import com.peaasecommerce.billiondaysale.model.Product;
import com.peaasecommerce.billiondaysale.repository.ProductRepository;
import com.peaasecommerce.billiondaysale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImple implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImple(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
