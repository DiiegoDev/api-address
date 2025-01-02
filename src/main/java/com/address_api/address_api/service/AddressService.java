package com.address_api.address_api.service;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

  private AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public String save(Address address) {
    this.addressRepository.save(address);

    return "Endereço salvo!!";
  }

  public List<Address> findAll() {
    var addressList = this.addressRepository.findAll();

    return addressList;
  }
}
