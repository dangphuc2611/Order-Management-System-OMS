package com.example.product_management.Model.request;

import com.example.product_management.Entity.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// UpdateOrderStatusRequest model to handle order status update requests
public class UpdateOrderStatusRequest {

    @NotNull
    private OrderStatus status;
}