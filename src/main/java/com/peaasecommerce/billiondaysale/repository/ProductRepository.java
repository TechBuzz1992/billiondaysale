package com.peaasecommerce.billiondaysale.repository;


import com.peaasecommerce.billiondaysale.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
