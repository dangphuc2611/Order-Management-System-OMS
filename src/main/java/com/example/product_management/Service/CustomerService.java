package com.example.product_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_management.Entity.Customers;
import com.example.product_management.Model.response.CustomersResponse;
import com.example.product_management.Repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository repository;

  // Get all customers from database
  public List<CustomersResponse> getAll() {
    return repository.findAll().stream().map(CustomersResponse::new).toList();
  }

}
