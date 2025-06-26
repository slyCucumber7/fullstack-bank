package com.github.slycucumber7.bank_web_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankWebServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testTest(){
        assertThat(1).isEqualTo(1);
    }

}
