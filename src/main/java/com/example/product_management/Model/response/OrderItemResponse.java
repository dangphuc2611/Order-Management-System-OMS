package com.example.product_management.Model.response;

import java.math.BigDecimal;

import com.example.product_management.Entity.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
// OrderItemResponse model to handle order item response data
public class OrderItemResponse {
  private Long id;

  private Long productId;

  private Integer quantity;

  private BigDecimal price;

  // Constructor to map OrderItem entity to OrderItemResponse
  public OrderItemResponse(OrderItem orderItem) {
    this.id = orderItem.getId();
    this.productId = orderItem.getProductId();
    this.quantity = orderItem.getQuantity();
    this.price = orderItem.getPrice();
  }

}
