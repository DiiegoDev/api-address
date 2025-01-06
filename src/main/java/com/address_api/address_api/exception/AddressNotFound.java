package com.address_api.address_api.exception;

public class AddressNotFound extends RuntimeException{
  public AddressNotFound() {super("Endereço não encontrado!");}

  public AddressNotFound(String message) {super(message);}
}
