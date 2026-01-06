package com.example.product_management.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// Orders Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_code", nullable = false, unique = true, length = 50)
  private String orderCode;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private OrderStatus status;

  @Column(name = "total_amount", precision = 12, scale = 2)
  private BigDecimal totalAmount;

  @Column(name = "customer_id", insertable = false, updatable = false)
  private Long customerId;

  // M Orders - 1 Customer
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customers customer;

  // 1 Order - M OrderItems
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

  // Automatically set createdAt, status, and totalAmount before persisting
  @PrePersist
  public void prePersist() {
    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
    if (status == null) {
      status = OrderStatus.NEW;
    }
    if (totalAmount == null) {
      totalAmount = BigDecimal.ZERO;
    }
  }
}
