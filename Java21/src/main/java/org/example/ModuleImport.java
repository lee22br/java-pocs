package org.example;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ModuleImport {

    public static void main (String[] args) throws SQLException {
        System.out.println("Test Module Import Declaration");

        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        System.out.println("Connection created: "+ (conn != null));

        ConnectionPoolDataSource ds = new org.h2.jdbcx.JdbcDataSource();
        DataSource dataSource = (DataSource) ds;
        System.out.println("Datasource created: "+ dataSource);
    }
}
