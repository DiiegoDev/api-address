package com.address_api.address_api.controller;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
import com.address_api.address_api.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping
  public ResponseEntity<String> saveAddress(@RequestBody @Valid AddressDTO request) {
    Address address = new Address(
        request.getCountry(),
        request.getState(),
        request.getCity(),
        request.getNeighborhood(),
        request.getPostalCode(),
        request.getStreet(),
        request.getNumber()
    );

    this.addressService.save(address);

    return ResponseEntity.ok("Endereço salvo!!");
  }

  @GetMapping
  public ResponseEntity<List<Address>> findAll() {

    return ResponseEntity.ok(this.addressService.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@RequestBody AddressDTO request, @PathVariable("id") UUID id) {

    this.addressService.update(request, id);

    return ResponseEntity.ok("Endereço atualizado!!");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    this.addressService.delete(id);
    return ResponseEntity.ok("Endereço apagado!!");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap();

    exception.getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError)error).getField();
      String message = error.getDefaultMessage();

      errors.put(fieldName, message);
    });

    return errors;
  }
}
