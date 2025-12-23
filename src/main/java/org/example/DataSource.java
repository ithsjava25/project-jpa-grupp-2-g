package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/app_db");
        config.setUsername("grupp2");
        config.setPassword(System.getenv("PASSWORD"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setAutoCommit(true);
        ds = new HikariDataSource(config);
    }

    private DataSource(){}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
