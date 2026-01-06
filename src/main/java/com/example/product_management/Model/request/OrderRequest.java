package com.example.product_management.Model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.product_management.Entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// OrderRequest model to handle order data
public class OrderRequest {

  private Long id;

  private String orderCode;

  private LocalDateTime createdAt;

  private BigDecimal totalAmount;

  private OrderStatus status;

  private Long customerId;

}
