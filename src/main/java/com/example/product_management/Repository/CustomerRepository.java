package com.example.product_management.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_management.Entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
  List<Customers> findByIsDeletedFalse();

  Page<Customers> findByIsDeletedFalse(Pageable pageable);

  Page<Customers> findByIsDeletedFalseAndNameContainingIgnoreCase(String name, Pageable pageable);
}
