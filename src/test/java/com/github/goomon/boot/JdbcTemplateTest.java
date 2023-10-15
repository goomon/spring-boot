package com.github.goomon.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@MySpringBootTest
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists member(name varchar(200) primary key, count int)");
    }

    @Test
    void insertAndQuery() {
        jdbcTemplate.update("insert into member values(?, ?)", "David", 3);
        jdbcTemplate.update("insert into member values(?, ?)", "Markus", 5);

        Long count = jdbcTemplate.queryForObject("select count(*) from member", Long.class);
        assertThat(count).isEqualTo(2);
    }
}
