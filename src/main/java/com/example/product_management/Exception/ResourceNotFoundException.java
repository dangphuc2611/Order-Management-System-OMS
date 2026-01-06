package com.example.product_management.Exception;

// ResourceNotFoundException class for handling resource not found scenarios
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String resourceName, Integer id) {
    super(String.format("%s with id %d not found", resourceName, id));
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
