package com.example.product_management.Model.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// OrderItemRequest model to handle order item data
public class OrderItemRequest {

  private Integer productId;

  private Integer quantity;

  private BigDecimal price;
}
