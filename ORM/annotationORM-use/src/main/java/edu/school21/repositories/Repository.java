package edu.school21.repositories;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
    private final DataSource dataSource;

    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean createTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS \"user\", \"car\" CASCADE;");
            statement.execute("CREATE TABLE IF NOT EXISTS \"user\" (\n" +
                    "\tid SERIAL PRIMARY KEY NOT NULL,\n" +
                    "\tfirstName VARCHAR (20) NOT NULL,\n" +
                    "\tlastName VARCHAR (20),\n" +
                    "\theight INTEGER\n" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS \"car\" (\n" +
                    "\tid SERIAL PRIMARY KEY NOT NULL,\n" +
                    "\tbrand VARCHAR (20) NOT NULL UNIQUE,\n" +
                    "\tmodel VARCHAR (20),\n" +
                    "\tcolor VARCHAR (20),\n" +
                    "\toperable BOOLEAN,\n" +
                    "\tyear INTEGER\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
    public void close() {
        if (dataSource instanceof Closeable) {
            try {
                ((Closeable) dataSource).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (dataSource instanceof AutoCloseable) {
            try {
                ((AutoCloseable) dataSource).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
