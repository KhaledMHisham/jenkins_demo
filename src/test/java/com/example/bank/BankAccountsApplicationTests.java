package com.example.bank;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankAccountsApplicationTests {

    @Test
    void pass(){

    }

    @Test
    @Disabled
    void fails(){
        assertThat(2).isEqualTo(3);
    }
}
