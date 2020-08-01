package com.invoice.validation.controller;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice-validation/transactions")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ValidationController {
  private final ValidationService service;

  @PostMapping
  public MessageDto validate(@RequestBody TransactionDto transactionDto) {
    return service.validate(transactionDto);
  }
}
