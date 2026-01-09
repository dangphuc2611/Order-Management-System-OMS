package com.example.product_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_management.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
