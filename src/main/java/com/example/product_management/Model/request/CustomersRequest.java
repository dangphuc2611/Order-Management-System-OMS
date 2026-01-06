package com.example.product_management.Model.request;

import com.example.product_management.Entity.Customers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// CustomersRequest model to handle customer data
public class CustomersRequest {
  private Integer id;

  private String name;

  private String phone;

  private String email;

}
