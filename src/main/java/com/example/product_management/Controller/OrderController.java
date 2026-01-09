package com.example.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_management.Entity.OrderStatus;
import com.example.product_management.Model.request.CreateOrderRequest;
import com.example.product_management.Model.request.UpdateOrderStatusRequest;
import com.example.product_management.Model.response.OrderResponse;
import com.example.product_management.Service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
  @Autowired
  private OrderService service;

  // Get all order in database
  @GetMapping("")
  public List<OrderResponse> getAll(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "100000") int pageSize,
      @RequestParam(required = false) OrderStatus status) {
    return service.getAll(pageNo, pageSize, status);
  }

  @DeleteMapping("/{id}")
  public void softDelete(@PathVariable Integer id) {
    service.softDeleteOrder(id);
  }

  // find 1 order in database
  @GetMapping("/{id}")
  public OrderResponse getOne(@PathVariable(name = "id") Integer id) {
    return service.getOne(id);
  }

  // create new order
  @PostMapping
  public OrderResponse createOrder(
      @RequestBody CreateOrderRequest request) {
    return service.createOrder(request);
  }

  // update order's status
  @PutMapping("/{id}/status")
  public OrderResponse updateStatus(
      @PathVariable Integer id,
      @RequestBody @Valid UpdateOrderStatusRequest request) {
    return service.updateOrderStatus(id, request);
  }

}
