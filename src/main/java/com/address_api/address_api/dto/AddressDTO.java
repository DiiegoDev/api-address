package com.address_api.address_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {
  @NotBlank(message = "Campo país não pode estar em branco!")
  String country;
  @NotBlank(message = "Campo estado não pode estar em branco!")
  String state;
  @NotBlank(message = "Campo cidade não pode estar em branco!")
  String city;
  @NotBlank(message = "Campo bairro não pode estar em branco!")
  String neighborhood;
  @NotBlank(message = "Campo cep não pode estar em branco!")
  String postalCode;
  @NotBlank(message = "Campo rua não pode estar em branco!")
  String street;
  Integer number;
}
