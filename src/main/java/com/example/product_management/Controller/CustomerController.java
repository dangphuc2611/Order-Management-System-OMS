package com.example.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_management.Entity.Customers;
import com.example.product_management.Model.request.CustomersRequest;
import com.example.product_management.Model.response.CustomersResponse;
import com.example.product_management.Repository.CustomerRepository;
import com.example.product_management.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
  @Autowired
  private CustomerService service;

  @Autowired
  private CustomerRepository repository;

  // Get all customers from database
  @GetMapping("")
  public List<CustomersResponse> getAll(
      @RequestParam(defaultValue = "0", required = true) int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(required = false) String name) {
    return service.getAll(pageNo, pageSize, name);
  }

  @PostMapping("")
  public void addCustomer(@RequestBody CustomersRequest request) {
    service.addCustomer(request);
  }

  @PutMapping("/{id}")
  public void updateCustomer(@RequestBody CustomersRequest request, @PathVariable(name = "id") Integer id) {
    service.updateCustomer(request, id);
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable(name = "id") Integer id) {
    Customers customers = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    customers.setIsDeleted(true);
    repository.save(customers);
  }
}
