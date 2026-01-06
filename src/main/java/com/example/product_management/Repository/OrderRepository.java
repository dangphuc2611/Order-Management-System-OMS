package com.example.product_management.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_management.Entity.Order;
import com.example.product_management.Entity.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  Page<Order> findByIsDeletedFalse(Pageable pageable);

  Page<Order> findByIsDeletedFalseAndStatus(OrderStatus status, Pageable pageable);

  List<Order> findByIsDeletedFalse();

  List<Order> findByIsDeletedFalseAndStatus(OrderStatus status);
}
