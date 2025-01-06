package com.address_api.address_api.exceptionHandler;

import com.address_api.address_api.exception.AddressNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(AddressNotFound.class)
  private ResponseEntity<String> addressNotFoundHandler() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não foi encontrado");
  }
}
