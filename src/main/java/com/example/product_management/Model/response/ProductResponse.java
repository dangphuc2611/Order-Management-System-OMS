package com.example.product_management.Model.response;

import java.math.BigDecimal;

import com.example.product_management.Entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
  private Integer id;

  private String name;

  private BigDecimal price;

  private Integer stock;

  public ProductResponse(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.stock = product.getStock();
  }

}
