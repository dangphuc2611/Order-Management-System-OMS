package com.example.product_management.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_management.Entity.Customers;
import com.example.product_management.Entity.Order;
import com.example.product_management.Entity.OrderItem;
import com.example.product_management.Entity.OrderStatus;
import com.example.product_management.Model.request.CreateOrderRequest;
import com.example.product_management.Model.request.OrderItemRequest;
import com.example.product_management.Model.request.UpdateOrderStatusRequest;
import com.example.product_management.Model.response.OrderResponse;
import com.example.product_management.Repository.CustomerRepository;
import com.example.product_management.Repository.OrderItemRepository;
import com.example.product_management.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
// OrderService to handle business logic related to orders
// Transactional to manage database transactions
// Transaction is important here to ensure data integrity when creating orders
// and order items
// * To put it simply: if 1 step fails, all previous steps are rolled back
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;

  private final OrderItemRepository orderItemRepository;

  private final CustomerRepository customerRepository;

  public OrderService(
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository,
      CustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.customerRepository = customerRepository;
  }

  // Get all orders from database
  public List<OrderResponse> getAll() {
    // return orderRepository.findAll().stream().map(OrderResponse::new).toList();
    return orderRepository.findByIsDeletedFalse()
        .stream()
        .map(this::mapToResponse)
        .toList();
  }

  // Get 1 order by ID
  public OrderResponse getOne(Integer id) {
    return orderRepository.findById(id).map(OrderResponse::new)
        .orElseThrow(() -> new RuntimeException("Order ID Not found"));
  }

  // Create a new order
  public OrderResponse createOrder(CreateOrderRequest request) {

    // 1. Find customer by ID
    Customers customer = customerRepository.findById(request.getCustomerId())
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    // 2. Create Order
    Order order = new Order();
    order.setOrderCode("ORD" + System.currentTimeMillis());
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);
    order.setCustomer(customer);

    // 3. Calculate totalAmount
    BigDecimal total = BigDecimal.ZERO;
    // ∑ (price × quantity)
    for (OrderItemRequest item : request.getItems()) {
      total = total.add(
          item.getPrice()
              .multiply(BigDecimal.valueOf(item.getQuantity())));
    }
    order.setTotalAmount(total);

    // 4. Save Order first
    Order savedOrder = orderRepository.save(order);

    // 5. Save OrderItem
    for (OrderItemRequest item : request.getItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(savedOrder);
      orderItem.setProductId(item.getProductId());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setPrice(item.getPrice());
      orderItemRepository.save(orderItem);
    }

    // 6. Return response
    return mapToResponse(savedOrder);
  }

  // Map Order entity to OrderResponse
  private OrderResponse mapToResponse(Order order) {
    return new OrderResponse(order);
  }

  // Update order status
  public OrderResponse updateOrderStatus(Integer orderId, UpdateOrderStatusRequest request) {

    // 1. Find order by ID
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));

    // 2. Validate status update
    if (order.getStatus() == OrderStatus.PAID) {
      throw new RuntimeException("Cannot update PAID order");
    }

    // 3. Update status
    order.setStatus(request.getStatus());

    // 4. Save
    orderRepository.save(order);

    // 5. Return response
    return mapToResponse(order);
  }

  @Transactional
  public void softDeleteOrder(Integer id) {

    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));

    if (order.getStatus() == OrderStatus.PAID) {
      throw new RuntimeException("Cannot delete PAID order");
    }

    order.setIsDeleted(true);
    orderRepository.save(order);
  }

}
