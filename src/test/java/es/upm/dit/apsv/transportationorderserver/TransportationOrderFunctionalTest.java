package es.upm.dit.apsv.transportationorderserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransportationOrderFunctionalTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Test funcional de la lista completa de órdenes
    @Test
    public void testGetOrdersFunctional() {
        String url = "http://localhost:" + port + "/transportationorders";

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
    }

    // Test funcional de una orden concreta (CAMBIA EL ID SI LO NECESITAS)
    @Test
    public void testGetOrderFunctional() {
        String id = "8962ZKR";  // Usa un ID que tengas en tu BD o repositorio
        String url = "http://localhost:" + port + "/transportationorders/" + id;

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        // Si existe, debería responder OK
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    // Test funcional del caso en que la orden NO existe
    @Test
    public void testGetOrderNotFoundFunctional() {
        String id = "XXXXX";  // Un ID que seguro no existe
        String url = "http://localhost:" + port + "/transportationorders/" + id;

        ResponseEntity<String> response =
                restTemplate.getForEntity(url, String.class);

        // Debe devolver 404 Not Found
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}
