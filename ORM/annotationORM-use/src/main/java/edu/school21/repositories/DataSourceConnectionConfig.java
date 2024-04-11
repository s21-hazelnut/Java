package edu.school21.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConnectionConfig {

    private final HikariDataSource dataSource;

    public DataSourceConnectionConfig() {
        try {
            Properties properties = new Properties();
            InputStream in = getClass().getResourceAsStream("/jdbc.properties");
            properties.load(in);
            String DB_USERNAME = (String) properties.get("USERNAME");
            String DB_PASSWORD = (String) properties.get("PASSWORD");
            String DB_URL = (String) properties.get("URL");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USERNAME);
            config.setPassword(DB_PASSWORD);
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}