package com.dawidantecki.watchers.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void should_connect_with_database() {
        try {
            dataSource.getConnection();
        } catch (SQLException ex) {
            fail("Cannot connect with the database");
        }
    }
}
