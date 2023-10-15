package com.github.goomon.boot.repository;

import com.github.goomon.boot.MySpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@MySpringBootTest
public class HelloRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HelloRepository helloRepository;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(200) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("David")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("David")).isEqualTo(0);

        helloRepository.increaseCount("David");
        assertThat(helloRepository.countOf("David")).isEqualTo(1);

        helloRepository.increaseCount("David");
        assertThat(helloRepository.countOf("David")).isEqualTo(2);
    }
}
