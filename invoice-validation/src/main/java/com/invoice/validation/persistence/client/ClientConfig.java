package com.invoice.validation.persistence.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = InvoicePersistenceClient.class)
public class ClientConfig {
}
