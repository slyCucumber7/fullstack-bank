package com.github.slycucumber7.web_server;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebServerApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    User jonDoe = new User(99L,123.45,"jonDoe");
    User janeDoe = new User(100L,997.12,"janeDoe");


    @Test
    void shouldReturnRequestedUser(){
        ResponseEntity<String> response = restTemplate
                .getForEntity("/users/",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(jonDoe.id());
//
//        Double balance = documentContext.read("$.balance");
//        assertThat(balance).isEqualTo(jonDoe.balance());
    }




}
