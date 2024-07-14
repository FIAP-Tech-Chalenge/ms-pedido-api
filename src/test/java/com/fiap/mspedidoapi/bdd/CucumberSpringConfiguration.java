package com.fiap.mspedidoapi.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.fiap.mspedidoapi.MsPedidoApiApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = MsPedidoApiApplication.class)
public class CucumberSpringConfiguration {
}
