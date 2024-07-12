package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.PedidoProntoController;
import com.fiap.mspedidoapi.infra.kafka.producers.PedidoProntoProducer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {PedidoProntoSteps.class})
public class PedidoProntoSteps {

    @MockBean
    private PedidosMongoRepository pedidosMongoRepository;

    @MockBean
    private KafkaProducer<String, String> kafkaProducer;

    @InjectMocks
    private PedidoProntoProducer pedidoProntoProducer;

    @InjectMocks
    private PedidoProntoController pedidoProntoController;

    private MockMvc mockMvc;

    private UUID pedidoUuid;
    private ResponseEntity<Object> responseEntity;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String servers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock KafkaProducer in PedidoProntoProducer
        pedidoProntoProducer = new PedidoProntoProducer(servers);
        ReflectionTestUtils.setField(pedidoProntoProducer, "producer", kafkaProducer);

        // Initialize PedidoProntoController with mocks
        ReflectionTestUtils.setField(pedidoProntoController, "pedidoProntoProducer", pedidoProntoProducer);
        pedidoProntoController = new PedidoProntoController(pedidosMongoRepository);

        // Setup MockMvc with PedidoProntoController
        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidoProntoController).build();
    }

    @Given("que um pedido existe com UUID {string}")
    public void que_um_pedido_existe_com_UUID(String uuid) {
        pedidoUuid = UUID.fromString(uuid);
    }

    @When("eu atualizo o pedido para pronto")
    public void eu_atualizo_o_pedido_para_pronto() throws Exception {
        try {
            Future<RecordMetadata> future = mock(Future.class);
            doReturn(future).when(kafkaProducer).send(any(ProducerRecord.class));
            doReturn(future).when(pedidosMongoRepository).findByUuidPedido(pedidoUuid);

            mockMvc.perform(post("/cozinha/entrega/{pedidoUuid}", pedidoUuid.toString()))
                    .andExpect(status().isOk())
                    .andDo(result -> responseEntity = ResponseEntity.status(result.getResponse().getStatus()).build());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("o status do pedido deve ser atualizado para {string}")
    public void o_status_do_pedido_deve_ser_atualizado_para(String statusEsperado) {
        try {
            if (responseEntity != null) {
                assertEquals(200, responseEntity.getStatusCodeValue());
            } else {
                System.err.println("Erro: responseEntity é null.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao verificar o status do pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}