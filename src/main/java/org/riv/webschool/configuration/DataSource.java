package org.riv.webschool.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class DataSource {
    private Connection connection;
    private Savepoint savepoint;

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/sch_student");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }
    // Connection only with transaction
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Connection connection = ds.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            this.connection = connection;
        }
        return connection;
    }

    public Connection getConnection(String savepointName) throws SQLException {
        if (connection == null || connection.isClosed()) {
            Connection connection = ds.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            this.savepoint = connection.setSavepoint(savepointName);
            this.connection = connection;
        }
        return connection;
    }

    public void rollbackConnection() throws SQLException {
        this.connection.rollback();
        this.connection.close();
        this.connection = null;
    }
    public void commit() throws SQLException {
        this.connection.commit();
    }

    public Savepoint getSavepoint() {
        return savepoint;
    }
}
