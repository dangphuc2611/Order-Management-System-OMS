package com.example.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_management.Model.response.ProductResponse;
import com.example.product_management.Repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
  @Autowired
  private ProductRepository repository;

  @GetMapping("")
  public List<ProductResponse> getAll() {
    return repository.findAll().stream().map(ProductResponse::new).toList();
  }
}
