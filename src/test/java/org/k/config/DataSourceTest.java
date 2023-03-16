package org.k.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        dataSource.getConnection();
    }
}