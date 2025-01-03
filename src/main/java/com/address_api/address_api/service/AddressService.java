package com.address_api.address_api.service;

import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
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

  public String save(Address address) {
    this.addressRepository.save(address);

    return "Endereço salvo!!";
  }

  public List<Address> findAll() {
    var addressList = this.addressRepository.findAll();

    return addressList;
  }

  public String update(AddressDTO request, UUID id) {
    Optional<Address> existingAddress = this.addressRepository.findById(id);

    if(existingAddress.isPresent()) {
      Address address = existingAddress.get();
      if(request.country() != null) address.setCountry(request.country());
      if(request.state() != null) address.setState(request.state());
      if(request.city() != null) address.setCity(request.city());
      if(request.neighborhood() != null) address.setNeighborhood(request.neighborhood());
      if(request.postalCode() != null) address.setPostalCode(request.postalCode());
      if(request.street() != null) address.setStreet(request.street());
      if(request.number() != null) address.setNumber(request.number());

      this.addressRepository.save(address);
    }

    return "Endereço atualizado!!";
  }

  public String delete(UUID id) {
    Optional<Address> address = this.addressRepository.findById(id);

    if(address.isPresent()) this.addressRepository.deleteById(id);

    return "Endereço apagado!!";
  }
}
