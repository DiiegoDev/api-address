package com.address_api.address_api.service;
import com.address_api.address_api.domain.Address;
import com.address_api.address_api.dto.AddressDTO;
import com.address_api.address_api.exception.AddressNotFound;
import com.address_api.address_api.repository.AddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private AddressService addressService;

  @Captor
  private ArgumentCaptor<Address> addressArgumentCaptor;

  @Captor
  private ArgumentCaptor<UUID> idArgumentCaptor;

  @Nested
  class saveAddress {
    @Test
    @DisplayName("Should save address succesfully")
    void save() {
      // Arrange
      Address address = new Address(
          UUID.randomUUID(),
          "Brasil",
          "Rio de Janeiro",
          "Volta Redonda",
          "Vila Nova",
          "25543123",
          "Alvares Cabral",
          123
      );

      Mockito.when(addressRepository.save(addressArgumentCaptor.capture())).thenReturn(address);

      // Act
      Address output = addressService.save(address);

      // Assert
      var addressCaputered = addressArgumentCaptor.getValue();
      assertThat(output).isNotNull();
      assertThat(address).usingRecursiveComparison().isEqualTo(addressCaputered);

      Mockito.verify(addressRepository, Mockito.times(1))
          .save(addressArgumentCaptor.capture());
    }
  }

  @Nested
  class findAllAdresses {
    @Test
    @DisplayName("Should successfully find a list of addresses")
    void shouldSuccessfullyFindAListOfAddresses() {
      // Arrange
      Address address = new Address(
          UUID.randomUUID(),
          "Brasil",
          "Rio de Janeiro",
          "Rio de Janeiro",
          "Ipanema",
          "24456788",
          "Almir Brito",
          213
      );

      Mockito.when(addressRepository.findAll()).thenReturn(List.of(address));

      // Act
      List<Address> output = addressService.findAll();

      // Assert
      assertThat(output).isNotNull();
      assertThat(output.size()).isEqualTo(1);

      Mockito.verify(addressRepository, Mockito.times(1))
          .findAll();
    }
  }

  @Nested
  class UpdateAddress {
    @Test
    @DisplayName("Should update address succesfully")
    void shouldUpdateAddressSuccesfully() {
      // Arrange
      UUID id = UUID.randomUUID();

      Address address = new Address(
          id,
          "Brasil",
          "Rio de Janeiro",
          "Rio de Janeiro",
          "Ipanema",
          "24456788",
          "Almir Brito",
          213
      );

      AddressDTO request = new AddressDTO();
      request.setStreet("Rua atualizada");

      Mockito.when(addressRepository.findById(idArgumentCaptor.capture()))
          .thenReturn(Optional.of(address));

      Mockito.when(addressRepository.save(addressArgumentCaptor.capture()))
          .thenAnswer(invocation -> invocation.getArgument(0));

      // Act
      Address output = addressService.update(request, id);

      // Assert
      var addressCatured = addressArgumentCaptor.getValue();
      var idCaptured = idArgumentCaptor.getValue();

      assertThat(output).isNotNull();
      assertThat(addressCatured.getStreet()).isEqualTo(request.getStreet());
      assertThat(id).isEqualTo(idCaptured);

      Mockito.verify(addressRepository, Mockito.times(1))
          .findById(idArgumentCaptor.capture());

      Mockito.verify(addressRepository, Mockito.times(1))
          .save(addressArgumentCaptor.capture());
    }

    @Test
    @DisplayName("It should throw an error when the address doesn't exist")
    void shouldThrowAnErrorWhenTheAddressDoesntExist() {
      // Arrange
      UUID id = UUID.randomUUID();

      AddressDTO request = new AddressDTO();
      request.setStreet("Rua atualizada");

      Mockito.when(addressRepository.findById(idArgumentCaptor.capture()))
          .thenReturn(Optional.empty());

      // Act and Assert
      assertThrows(AddressNotFound.class, () -> addressService.update(request, id));

      Mockito.verify(addressRepository, Mockito.times(1)).findById(id);
      Mockito.verify(addressRepository, Mockito.never()).save(Mockito.any());
    }
  }

  @Nested
  class DeleteAddress {
    @Test
    @DisplayName("Should delete address succesfully")
    void shouldDeleteAddressSuccesfully() {
      // Arrange
      UUID id = UUID.randomUUID();

      Address address = new Address();

      Mockito.when(addressRepository.findById(idArgumentCaptor.capture()))
          .thenReturn(Optional.of(address));

      Mockito.doNothing().when(addressRepository).deleteById(idArgumentCaptor.capture());

      // Act
      Boolean output = addressService.delete(id);

      // Assert
      var idCaptured = idArgumentCaptor.getValue();

      assertThat(output).isTrue();
      assertThat(idCaptured).isEqualTo(id);

      Mockito.verify(addressRepository, Mockito.times(1))
          .findById(idArgumentCaptor.capture());

      Mockito.verify(addressRepository, Mockito.times(1))
          .deleteById(idArgumentCaptor.capture());

    }

    @Test
    @DisplayName("It should throw an error when the address doesn't exist")
    void shouldThrowAnErrorWhenTheAddressDoesntExist() {
      // Arrange
      UUID id = UUID.randomUUID();

      Mockito.when(addressRepository.findById(idArgumentCaptor.capture()))
          .thenReturn(Optional.empty());

      // Act and Assert
      assertThrows(AddressNotFound.class, () -> addressService.delete(id));

      var idCaptured = idArgumentCaptor.getValue();

      assertThat(idCaptured).isEqualTo(id);

      Mockito.verify(addressRepository, Mockito.times(1))
          .findById(idArgumentCaptor.capture());

      Mockito.verify(addressRepository, Mockito.never()).deleteById(Mockito.any());

    }
  }
}