package com.example.product_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public List<CustomersResponse> getAll(Integer pageNo, Integer pageSize, String name) {
    // List<Customers> pageCustomers = repository.findByIsDeletedFalse();
    // List<CustomersResponse> pageResponse =
    // pageCustomers.stream().map(CustomersResponse::new).toList();
    // return pageResponse;

    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Customers> pageCustomers;
    if (name != null) {
      pageCustomers = repository.findByIsDeletedFalseAndNameContainingIgnoreCase(name, pageable);
    } else {
      pageCustomers = repository.findByIsDeletedFalse(pageable);
    }
    // map from Order => Order Response
    List<CustomersResponse> pageResponse = pageCustomers.map(CustomersResponse::new).getContent();
    return pageResponse;
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
