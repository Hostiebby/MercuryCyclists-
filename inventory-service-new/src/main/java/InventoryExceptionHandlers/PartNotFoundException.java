package InventoryExceptionHandlers;

public class PartNotFoundException extends RuntimeException {

  public PartNotFoundException(Integer partId) {
    super("Could not find contact " + partId);
  }
}