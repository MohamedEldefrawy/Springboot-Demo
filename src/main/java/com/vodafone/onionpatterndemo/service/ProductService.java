package com.vodafone.onionpatterndemo.service;

import com.vodafone.onionpatterndemo.errorhandling.ProdutNotFoundException;
import com.vodafone.onionpatterndemo.model.Product;
import com.vodafone.onionpatterndemo.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements CustomService<Product> {

  private final ProductRepository productRepository;

  @Override
  public Product save(Product product) {
    return this.productRepository.save(product);
  }

  @Override
  public List<Product> findAll() {
    return this.productRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    this.productRepository.deleteById(id);
  }

  @Override
  public Product update(Product product) {
    return this.productRepository.save(product);
  }

  @Override
  public Product findById(Long id) {
    return this.productRepository.findById(id).orElseThrow(() -> new ProdutNotFoundException("couldn't find product with id: " + id));
  }

  @PreFilter("filterObject.user.email == authentication.name")
  public Optional<List<Product>> sellProducts(List<Product> products) {
    log.info("Products has been sold");
    return Optional.of(products);
  }
}
