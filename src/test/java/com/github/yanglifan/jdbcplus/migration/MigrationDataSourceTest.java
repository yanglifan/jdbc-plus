package com.github.yanglifan.jdbcplus.migration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MigrationDataSourceTest {
    HikariDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:~/test");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");

        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();

        String ddlFile = this.getClass().getClassLoader().getResource("create-tables.sql").getPath();
        String ddl = FileUtils.readFileToString(new File(ddlFile), "UTF-8");
        statement.execute(ddl);

        statement.execute("insert into TEST values ( 1, 1 )");
        statement.execute("insert into TEST_NEW values ( 1, 1 )");
    }

    @Test
    public void demo() throws SQLException {
        // Given
        TableAndDataSource source = new TableAndDataSource();
        source.tableName = "test";
        source.dataSource = dataSource;

        TableAndDataSource target = new TableAndDataSource();
        target.tableName = "test_new";
        target.dataSource = dataSource;

        MigrationContext migrationContext = new MigrationContext();
        migrationContext.addMigrationPair(source, target);

        MigrationDataSource migrationDataSource = new MigrationDataSource();
        migrationDataSource.setMigrationContext(migrationContext);

        ResultSet rs = dataSource.getConnection().createStatement().executeQuery("select k from test where id = 1");
        rs.next();
        int k = rs.getInt(1);

        // When
        Connection connection = migrationDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update test set k = k + 1 where id = 1");
        int i = preparedStatement.executeUpdate();

        // Then
        assertThat(i, is(1));

        rs = dataSource.getConnection().createStatement().executeQuery("select k from test where id = 1");
        rs.next();
        int newK = rs.getInt(1);
        assertThat(newK, is(k + 1));

        rs = dataSource.getConnection().createStatement().executeQuery("select k from test_new where id = 1");
        rs.next();
        newK = rs.getInt(1);
        assertThat(newK, is(k + 1));
    }
}