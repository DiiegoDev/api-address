package com.address_api.address_api.controller;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
import com.address_api.address_api.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping
  public ResponseEntity<String> saveAddress(@RequestBody AddressDTO request) {
    Address address = new Address(
        UUID.randomUUID(),
        request.country(),
        request.state(),
        request.city(),
        request.neighborhood(),
        request.postalCode(),
        request.street(),
        request.number()
    );

    return ResponseEntity.ok(this.addressService.save(address));
  }

  @GetMapping
  public ResponseEntity<List<Address>> findAll() {
    System.out.println(this.addressService.findAll());
    return ResponseEntity.ok(this.addressService.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@RequestBody AddressDTO request, @PathVariable("id") UUID id) {
    return ResponseEntity.ok(this.addressService.update(request, id));
  }
}
