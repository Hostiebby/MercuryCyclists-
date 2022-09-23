package InventoryExceptionHandlers;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Integer productId) {
    super("Could not find contact " + productId);
  }
}