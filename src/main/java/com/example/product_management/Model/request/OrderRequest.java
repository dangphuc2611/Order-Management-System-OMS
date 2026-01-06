package com.example.product_management.Model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.product_management.Entity.OrderStatus;

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
