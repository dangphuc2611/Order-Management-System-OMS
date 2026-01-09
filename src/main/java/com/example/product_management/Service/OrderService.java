package com.example.product_management.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product_management.Entity.Customers;
import com.example.product_management.Entity.Order;
import com.example.product_management.Entity.OrderItem;
import com.example.product_management.Entity.OrderStatus;
import com.example.product_management.Entity.Product;
import com.example.product_management.Model.request.CreateOrderRequest;
import com.example.product_management.Model.request.OrderItemRequest;
import com.example.product_management.Model.request.UpdateOrderStatusRequest;
import com.example.product_management.Model.response.OrderResponse;
import com.example.product_management.Repository.CustomerRepository;
import com.example.product_management.Repository.OrderItemRepository;
import com.example.product_management.Repository.OrderRepository;
import com.example.product_management.Repository.ProductRepository;

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

  private final ProductRepository productRepository;

  public OrderService(
      OrderRepository orderRepository,
      OrderItemRepository orderItemRepository,
      CustomerRepository customerRepository,
      ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  // Get all orders from database(update: Pageable, find by status)
  public List<OrderResponse> getAll(Integer pageNo, Integer pageSize, OrderStatus status) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Order> pageOrders;
    if (status != null) {
      pageOrders = orderRepository.findByIsDeletedFalseAndStatus(status, pageable);
    } else {
      pageOrders = orderRepository.findByIsDeletedFalse(pageable);
    }
    // map from Order => Order Response
    List<OrderResponse> pageResponse = pageOrders.map(OrderResponse::new).getContent();
    return pageResponse;
  }

  // Get 1 order by ID
  public OrderResponse getOne(Integer id) {
    return orderRepository.findById(id).map(OrderResponse::new)
        .orElseThrow(() -> new RuntimeException("Order ID Not found"));
  }

  // Create a new order
  @Transactional
  public OrderResponse createOrder(CreateOrderRequest request) {

    // 1. Check customer
    Customers customer = customerRepository.findById(request.getCustomerId())
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    // 2. Create Order (chưa set total)
    Order order = new Order();
    order.setOrderCode("ORD" + System.currentTimeMillis());
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);
    order.setCustomer(customer);
    order.setTotalAmount(BigDecimal.ZERO);

    orderRepository.save(order);

    BigDecimal totalAmount = BigDecimal.ZERO;

    // 3. Handle OrderItems
    for (OrderItemRequest item : request.getItems()) {

      Product product = productRepository.findById(item.getProductId())
          .orElseThrow(() -> new RuntimeException("Product not found"));

      if (product.getStock() < item.getQuantity()) {
        throw new RuntimeException("Product out of stock");
      }

      // giảm tồn kho
      product.setStock(product.getStock() - item.getQuantity());

      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setProductId(product.getId());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setPrice(product.getPrice()); // snapshot giá

      orderItemRepository.save(orderItem);

      totalAmount = totalAmount.add(
          product.getPrice().multiply(
              BigDecimal.valueOf(item.getQuantity())));
    }

    // 4. Update totalAmount
    order.setTotalAmount(totalAmount);
    orderRepository.save(order);

    return mapToResponse(order);
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
