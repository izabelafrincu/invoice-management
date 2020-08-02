package com.invoice.persistence.service;

import com.invoice.persistence.PersistenceApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@ComponentScan("com.invoice.persistence")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PersistenceApp.class)
public abstract class BaseIT {
}
