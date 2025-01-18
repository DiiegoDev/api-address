package com.address_api.address_api.service;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
import com.address_api.address_api.exception.AddressNotFound;
import com.address_api.address_api.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

  private AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public Address save(Address address) {

    return this.addressRepository.save(address);
  }

  public List<Address> findAll() {
    var addressList = this.addressRepository.findAll();

    return addressList;
  }

  public Address update(AddressDTO request, UUID id) {
    Optional<Address> existingAddress = this.addressRepository.findById(id);

    if(existingAddress.isEmpty()) throw new AddressNotFound();

    Address address = existingAddress.get();

    if(request.getCountry() != null) address.setCountry(request.getCountry());
    if(request.getState() != null) address.setState(request.getState());
    if(request.getCity() != null) address.setCity(request.getCity());
    if(request.getNeighborhood() != null) address.setNeighborhood(request.getNeighborhood());
    if(request.getPostalCode() != null) address.setPostalCode(request.getPostalCode());
    if(request.getStreet() != null) address.setStreet(request.getStreet());
    if(request.getNumber() != null) address.setNumber(request.getNumber());

    return this.addressRepository.save(address);
  }

  public Boolean delete(UUID id) {
    Optional<Address> address = this.addressRepository.findById(id);

    if(address.isEmpty()) throw new AddressNotFound();

    this.addressRepository.deleteById(id);

    return true;
  }
}
