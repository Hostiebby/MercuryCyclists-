package exceptionHandlers;

public class ContactNotFoundException extends RuntimeException {

  public ContactNotFoundException(String name) {
    super("Could not find contact " + name);
  }
}