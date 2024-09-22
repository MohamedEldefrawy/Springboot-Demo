package com.vodafone.onionpatterndemo.controller;

import com.vodafone.onionpatterndemo.model.Product;
import com.vodafone.onionpatterndemo.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class ProductController {

  private final ProductService productService;

  @GetMapping("/sell")
  public ResponseEntity<List<Product>> sellProducts(@RequestBody List<Product> products) {
    Optional<List<Product>> optionalProductList = productService.sellProducts(products);
    return ResponseEntity.ok(optionalProductList.get());
  }
}
