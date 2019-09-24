package com.peaasecommerce.billiondaysale.service;

import com.peaasecommerce.billiondaysale.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

}
