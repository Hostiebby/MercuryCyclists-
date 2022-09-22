package exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SupplierNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(SupplierNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String supplierNotFoundHandler(SupplierNotFoundException ex) {
    return ex.getMessage();
  }
}