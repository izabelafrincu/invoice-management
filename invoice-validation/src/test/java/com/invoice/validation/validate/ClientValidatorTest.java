package com.invoice.validation.validate;

import com.invoice.validation.dto.TransactionDto;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class ClientValidatorTest {
  private static final String INVALID_IBAN = "RO22TEST878989890909874";
  private static final String VALID_IBAN = "RO22TEST8789898909098749";
  private static final String CNP = "1234567890138";
  private static final String NAME = "ION Ionescu";
  @InjectMocks
  private ClientValidator sut;

  @Test
  public void validateClient_ReturnsErrorMessage_IfIBANIsInvalid() {
    TransactionDto.ClientDto clientDto = mockClientDto(INVALID_IBAN);
    Collection<String> result = sut.validateClient(clientDto);

    assertTrue(result.contains(String.format("Client=%s has invalid IBAN=%s", NAME, INVALID_IBAN)));
  }

  @Test
  public void validateClient_ReturnsNoError_IfClientHasValidFields() {
    TransactionDto.ClientDto clientDto = mockClientDto(VALID_IBAN);
    Collection<String> result = sut.validateClient(clientDto);

    assertTrue(result.isEmpty());
  }

  private TransactionDto.ClientDto mockClientDto(String IBAN) {
    return new TransactionDto.ClientDto(IBAN, CNP, NAME);
  }
}