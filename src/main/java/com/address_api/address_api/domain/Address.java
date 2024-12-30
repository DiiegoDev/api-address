package com.address_api.address_api.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "address")
@Entity
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

  public Address() {}

  private Address(Builder builder) {
    this.country = builder.country;
    this.state = builder.state;
    this.city = builder.city;
    this.neighborhood = builder.neighborhood;
    this.postalCode = builder.postalCode;
    this.street = builder.street;
    this.number = builder.number;
  }

  public static class Builder {
    String country;
    String state;
    String city;
    String neighborhood;
    String postalCode;
    String street;
    Integer number;

    public Builder setCountry(String country) {
      this.country = country;
      return this;
    }

    public Builder setState(String state) {
      this.state = state;
      return this;
    }

    public Builder setCity(String city) {
      this.city = city;
      return this;
    }

    public Builder setNeighborhood(String neighborhood) {
      this.neighborhood = neighborhood;
      return this;
    }

    public Builder setPostalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder setStreet(String street) {
      this.street = street;
      return this;
    }

    public Builder setNumber(Integer number) {
      this.number = number;
      return this;
    }

    public Address build() {
      return new Address(this);
    }
  }

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

