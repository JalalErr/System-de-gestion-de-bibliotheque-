package com.usermanager.util;

import com.usermanager.config.DatabaseConfig;
import com.usermanager.exception.DatabaseConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final HikariDataSource dataSource;

    private DatabaseConnection() {
        try {
            Class.forName(DatabaseConfig.getDriver());

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DatabaseConfig.getUrl());
            config.setUsername(DatabaseConfig.getUsername());
            config.setPassword("");
//            config.setConnectionTimeout(DatabaseConfig.getLongProperty("hikari.connectionTimeout", 30000));
//            config.setIdleTimeout(DatabaseConfig.getLongProperty("hikari.idleTimeout", 600000));
//            config.setMaxLifetime(DatabaseConfig.getLongProperty("hikari.maxLifetime", 1800000));
//            config.setMinimumIdle(DatabaseConfig.getIntProperty("hikari.minimumIdle", 5));
//            config.setMaximumPoolSize(DatabaseConfig.getIntProperty("hikari.maximumPoolSize", 20));
//            config.setPoolName(DatabaseConfig.getProperty("hikari.poolName"));
//            config.setAutoCommit(Boolean.parseBoolean(DatabaseConfig.getProperty("hikari.autoCommit")));
//
//            config.addDataSourceProperty("cachePrepStmts", DatabaseConfig.getProperty("hikari.cachePrepStmts"));
//            config.addDataSourceProperty("prepStmtCacheSize", DatabaseConfig.getProperty("hikari.prepStmtCacheSize"));
//            config.addDataSourceProperty("prepStmtCacheSqlLimit", DatabaseConfig.getProperty("hikari.prepStmtCacheSqlLimit"));

            dataSource = new HikariDataSource(config);
            System.out.println("✓ Database connection pool initialized successfully");

        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("JDBC Driver not found", e);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to initialize connection pool", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("✓ Database connection pool closed");
        }
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}