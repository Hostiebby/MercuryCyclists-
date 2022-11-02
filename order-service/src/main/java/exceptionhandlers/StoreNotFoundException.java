package exceptionhandlers;

public class StoreNotFoundException extends RuntimeException {

  public StoreNotFoundException(Integer storeId) {
    super("Could not find store " + storeId);
  }
}