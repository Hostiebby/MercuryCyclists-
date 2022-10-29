package exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PartNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(PartNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String PartNotFoundHandler(PartNotFoundException ex) {
    return ex.getMessage();
  }
}