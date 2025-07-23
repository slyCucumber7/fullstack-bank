package com.github.slycucumber7.web_server;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebServerApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    BankCustomer jonDoe = new BankCustomer(99L,123.45);
    BankCustomer janeDoe = new BankCustomer(100L,997.12);


    @Test
    void invalidIdShouldReturnNotFound(){
        ResponseEntity<String> response = restTemplate
                .getForEntity("/users/67",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void validIdShouldReturnUser(){
        ResponseEntity<String> response = restTemplate
                .getForEntity("/users/99",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo((int)jonDoe.id);
        Double balance = documentContext.read("$.balance");
        assertThat(balance).isEqualTo(jonDoe.balance);

    }

    @Test
    @DirtiesContext
    void shouldCreateANewUser(){
        BankCustomer newUser = new BankCustomer(0, 250.32);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/users",newUser,Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    URI userLocation = response.getHeaders().getLocation();
    ResponseEntity<String> getResponse = restTemplate
            .getForEntity(userLocation,String.class);
    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
    Number id = documentContext.read("$.id");
    Double balance = documentContext.read("$.balance");

    assertThat(id).isNotNull();
    assertThat(balance).isEqualTo(newUser.balance);

    }

    


}
