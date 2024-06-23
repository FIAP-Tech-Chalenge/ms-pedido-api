package com.fiap.mspedidoapi;

import com.fiap.mspedidoapi.infra.kafka.consumers.PedidoConsumer;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsPedidoApiApplication {

    private final PedidoConsumer consumer;

    public MsPedidoApiApplication(PedidoConsumer consumer) {
        this.consumer = consumer;
    }

    public static void main(String[] args) {
        SpringApplication.run(MsPedidoApiApplication.class, args);
    }

    @PostConstruct
    public void startConsumer() {
        Thread consumerThread = new Thread(consumer::runConsumer);
        consumerThread.start();
    }

}
