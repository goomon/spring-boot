package com.github.goomon.boot.repository;

import com.github.goomon.boot.model.Hello;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject("select * from hello where name = '" + name + "'",
                    (rs, rowNum) -> new Hello(
                            rs.getString("name"), rs.getLong("count")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null) jdbcTemplate.update("insert into hello values(?, ?)", name, 1);
        else jdbcTemplate.update("update hello set count = ? where name = ?", hello.getCount() + 1, name);
    }

    @PostConstruct
    private void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(200) primary key, count int)");
    }
}
