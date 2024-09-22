package com.vodafone.onionpatterndemo.repository;

import com.vodafone.onionpatterndemo.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
