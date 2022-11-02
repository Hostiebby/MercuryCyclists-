package exceptionhandlers;

public class SaleNotFoundException extends RuntimeException {

  public SaleNotFoundException(Integer salesId) {
    super("Could not find sale " + salesId);
  }
}