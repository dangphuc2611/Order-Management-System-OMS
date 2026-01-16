package com.example.product_management.Model.response;

import java.math.BigDecimal;

import com.example.product_management.Entity.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// OrderItemResponse model to handle order item response data
public class OrderItemResponse {
  private Long id;

  private String productName;

  private Integer quantity;

  private BigDecimal price;

  // Constructor to map OrderItem entity to OrderItemResponse
  public OrderItemResponse(OrderItem orderItem) {
    this.id = orderItem.getId();
    this.productName = orderItem.getProduct().getName();
    this.quantity = orderItem.getQuantity();
    this.price = orderItem.getPrice();
  }

}
