package com.dawidantecki.watchers.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Connection

@SpringBootTest
class DatabaseConnectionTest extends Specification {

    @Autowired
    private DataSource dataSource

    def "should connect to the database"() {
        when:
        Connection conn = dataSource.getConnection()
        then:
        !conn.isClosed()
    }
}
