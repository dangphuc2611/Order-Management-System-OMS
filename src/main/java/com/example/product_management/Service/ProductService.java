package com.example.product_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product_management.MapperUtil;
import com.example.product_management.Entity.Product;
import com.example.product_management.Model.request.ProductRequest;
import com.example.product_management.Model.response.ProductResponse;
import com.example.product_management.Repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  public Page<ProductResponse> getAll(Integer pageNo, Integer pageSize) {
    // return repository.findAll().stream().map(ProductResponse::new).toList();
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Product> pageProduct;

    pageProduct = repository.findAll(pageable);

    // map from Order => Order Response
    Page<ProductResponse> pageResponse = pageProduct.map(ProductResponse::new);
    return pageResponse;
  }

  public void addProduct(ProductRequest request) {
    Product product = MapperUtil.map(request, Product.class);
    repository.save(product);
  }

  public void updateProduct(ProductRequest request, Integer id) {
    MapperUtil.mapToExisting(request, Product.class);
    Product productExist = repository.findById(id).get();
    MapperUtil.mapToExisting(request, productExist);
    // Boi vi sau mapping id se chuyen ve null theo id cua request nen can set lai
    productExist.setId(id);
    repository.save(productExist);
  }

}
