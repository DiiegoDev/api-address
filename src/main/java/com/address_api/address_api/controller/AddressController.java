package com.address_api.address_api.controller;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
import com.address_api.address_api.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
  private AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping
  public ResponseEntity<String> saveAddress(@RequestBody AddressDTO request) {
    Address address = new Address.Builder()
        .setCountry(request.country())
        .setState(request.state())
        .setCity(request.city())
        .setNeighborhood(request.neighborhood())
        .setPostalCode(request.postalCode())
        .setStreet(request.street())
        .setNumber(request.number())
        .build();

    return ResponseEntity.ok(this.addressService.save(address));
  }

  @GetMapping
  public ResponseEntity<List<Address>> findAll() {
    System.out.println(this.addressService.findAll());
    return ResponseEntity.ok(this.addressService.findAll());
  }
}
