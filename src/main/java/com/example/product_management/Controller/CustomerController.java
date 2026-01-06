package com.example.product_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_management.Model.request.CustomersRequest;
import com.example.product_management.Model.response.CustomersResponse;
import com.example.product_management.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  @Autowired
  private CustomerService service;

  // Get all customers from database
  @GetMapping("")
  public List<CustomersResponse> getAll() {
    return service.getAll();
  }

  @PostMapping("")
  public void addCustomer(@RequestBody CustomersRequest request) {
    service.addCustomer(request);
  }

  @PutMapping("/{id}")
  public void updateCustomer(@RequestBody CustomersRequest request, @PathVariable(name = "id") Integer id) {
    service.updateCustomer(request, id);
  }
}
