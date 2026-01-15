package com.example.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_management.MapperUtil;
import com.example.product_management.Entity.Customers;
import com.example.product_management.Entity.Product;
import com.example.product_management.Model.request.CustomersRequest;
import com.example.product_management.Model.request.ProductRequest;
import com.example.product_management.Model.response.CustomersResponse;
import com.example.product_management.Model.response.ProductResponse;
import com.example.product_management.Repository.ProductRepository;
import com.example.product_management.Service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
  @Autowired
  private ProductRepository repository;
  @Autowired
  private ProductService service;

  @GetMapping("")
  public Page<ProductResponse> getAll(
      @RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
      @RequestParam(name = "pageSize", required = false) Integer pageSize) {
    return service.getAll(pageNo, pageSize = 10);
  }

  @PostMapping("")
  public void addProduct(@RequestBody ProductRequest request) {
    service.addProduct(request);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable(name = "id") Integer id) {
    if (!repository.existsById(id)) {
      throw new RuntimeException("ID Not Found");
    }
    repository.deleteById(id);
  }

  @PutMapping("/{id}")
  public void updateProduct(@RequestBody ProductRequest request, @PathVariable(name = "id") Integer id) {
    service.updateProduct(request, id);
  }
}
