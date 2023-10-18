package com.github.goomon.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@JdbcTest
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
