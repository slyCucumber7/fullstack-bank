package com.github.slycucumber7.web_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class WebServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void exampleTest(){
        assertThat(1).isEqualTo(2);
    }

}
