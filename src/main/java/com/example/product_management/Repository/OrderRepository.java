package com.example.product_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_management.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
