package com.example.product_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_management.Entity.Customers;
import com.example.product_management.MapperUtil;
import com.example.product_management.Model.request.CustomersRequest;
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

  public void addCustomer(CustomersRequest request) {
    Customers customers = MapperUtil.map(request, Customers.class);
    repository.save(customers);
  }

  public void updateCustomer(CustomersRequest request, Integer id) {
    // B1: Mapping tu request ve entity
    MapperUtil.mapToExisting(request, Customers.class);
    Customers customersExist = repository.findById(id).get();
    MapperUtil.mapToExisting(request, customersExist);
    // Boi vi sau mapping id se chuyen ve null theo id cua request nen can set lai
    customersExist.setId(id);
    repository.save(customersExist);

  }

}
