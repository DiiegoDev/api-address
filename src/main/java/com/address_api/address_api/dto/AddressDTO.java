package com.address_api.address_api.dto;

public record AddressDTO(
    String country,
    String state,
    String city,
    String neighborhood,
    String postalCode,
    String street,
    Integer number
) {}
