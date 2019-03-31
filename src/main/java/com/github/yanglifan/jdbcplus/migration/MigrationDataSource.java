package com.github.yanglifan.jdbcplus.migration;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class MigrationDataSource implements DataSource {
    private DataSource defaultDataSource;

    private MigrationContext migrationContext;

    public void setMigrationContext(MigrationContext migrationContext) {
        this.migrationContext = migrationContext;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new MigrationConnection(migrationContext);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    private void buildMigrationContext() {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }


}
