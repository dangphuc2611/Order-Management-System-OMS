package com.example.product_management.Model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.product_management.Entity.Order;
import com.example.product_management.Entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
// OrderResponse model to handle order response data
public class OrderResponse {

  private Long id;

  private String orderCode;

  private LocalDateTime createdAt;

  private BigDecimal totalAmount;

  private OrderStatus status;

  private Long customerId;

  private String customerName;

  // Constructor to map Order entity to OrderResponse
  public OrderResponse(Order order) {
    this.id = order.getId();
    this.orderCode = order.getOrderCode();
    this.createdAt = order.getCreatedAt();
    this.totalAmount = order.getTotalAmount();
    this.status = order.getStatus();
    this.customerId = order.getCustomerId();
    this.customerName = order.getCustomer().getName();
  }

}
