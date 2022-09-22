package exceptionHandlers;

public class SupplierNotFoundException extends RuntimeException {

  public SupplierNotFoundException(String companyName) {
    super("Could not find supplier " + companyName);
  }
}