package com.example.product_management.Model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.product_management.Entity.Order;
import com.example.product_management.Entity.OrderStatus;

import lombok.Getter;
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

  private List<OrderItemResponse> orderItems;

  // Constructor to map Order entity to OrderResponse
  public OrderResponse(Order order) {
    this.id = order.getId();
    this.orderCode = order.getOrderCode();
    this.createdAt = order.getCreatedAt();
    this.totalAmount = order.getTotalAmount();
    this.status = order.getStatus();
    this.customerId = order.getCustomerId();
    this.customerName = order.getCustomer().getName();
    this.orderItems = order.getOrderItems().stream().map(OrderItemResponse::new).toList();
  }

}
