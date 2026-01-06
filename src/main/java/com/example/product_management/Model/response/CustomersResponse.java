package com.example.product_management.Model.response;

import com.example.product_management.Entity.Customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// CustomersResponse model to handle customer response data
public class CustomersResponse {
  private Integer id;

  private String name;

  private String phone;

  private String email;

  // Constructor to map Customers entity to CustomersResponse
  public CustomersResponse(Customers customers) {
    this.id = customers.getId();
    this.name = customers.getName();
    this.phone = customers.getPhone();
    this.email = customers.getEmail();
  }
}
