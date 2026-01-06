package com.example.product_management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.product_management.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  @Query(value = "SELECT * FROM orders WHERE isdelete is :isDelete", nativeQuery = true)
  List<Order> getOrdersByIsDelete(Boolean isDelete);
}
