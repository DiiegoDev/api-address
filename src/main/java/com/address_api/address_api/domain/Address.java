package com.address_api.address_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "address")
@Entity(name = "address")
@Getter
@Setter
@AllArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;
  String country;
  String state;
  String city;
  String neighborhood;
  String postalCode;
  String street;
  Integer number;

  public Address(String country, String state, String city, String neighborhood, String postalCode, String street, Integer number) {
    this.country = country;
    this.state = state;
    this.city = city;
    this.neighborhood = neighborhood;
    this.postalCode = postalCode;
    this.street = street;
    this.number = number;
  }

  public Address() {}

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", country='" + country + '\'' +
        ", state='" + state + '\'' +
        ", city='" + city + '\'' +
        ", neighborhood='" + neighborhood + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", street='" + street + '\'' +
        ", number=" + number +
        '}';
  }
}

