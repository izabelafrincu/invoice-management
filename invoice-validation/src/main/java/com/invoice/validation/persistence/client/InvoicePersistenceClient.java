package com.invoice.validation.persistence.client;

import com.invoice.shared.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invoicePersistenceClient", url = "${client.service.url}")
public interface InvoicePersistenceClient {

  @PostMapping("/invoice-persistence/load/transactions")
  Long load(@RequestBody TransactionDto request);
}
