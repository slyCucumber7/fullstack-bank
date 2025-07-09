package com.github.slycucumber7.web_server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;




import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebServerApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void exampleTest(){
        assertThat(1).isEqualTo(2);
    }





}
