package com.invoice.persistence.controller;

import com.invoice.persistence.service.PersistenceService;
import com.invoice.shared.dto.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice-persistence/load/transactions")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PersistenceController {
  private PersistenceService service;

  @PostMapping
  public Long load(@RequestBody TransactionDto request) {
    return service.loadData(request);
  }
}
