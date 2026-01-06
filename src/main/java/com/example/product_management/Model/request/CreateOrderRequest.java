package com.example.product_management.Model.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// CreateOrderRequest model to handle order creation requests
public class CreateOrderRequest {
    @NotNull
    private Integer customerId;
    // 1 Order - M OrderItems -> List of OrderItemRequest
    private List<OrderItemRequest> items;
}
