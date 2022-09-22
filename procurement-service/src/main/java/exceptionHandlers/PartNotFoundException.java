package exceptionHandlers;

public class PartNotFoundException extends RuntimeException {

  public PartNotFoundException(String partId) {
    super("Could not find contact " + partId);
  }
}