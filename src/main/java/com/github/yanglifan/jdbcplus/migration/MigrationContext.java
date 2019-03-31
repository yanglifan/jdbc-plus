package com.github.yanglifan.jdbcplus.migration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Provide the concrete database connection according to the operation info.
 */
public class MigrationContext {

    private DataSource defaultDataSource;

    private Map<String, TableAndDataSource> sourceMapping = new HashMap<>();
    private Map<String, TableAndDataSource> sourceToTargetMapping = new HashMap<>(); // Key is the source table name;

    public void addMigrationPair(TableAndDataSource source, TableAndDataSource target) {
        sourceMapping.put(source.tableName, source);
        sourceToTargetMapping.put(source.tableName, target);
    }

    public MigrationPair getMigrationPair(String sourceTableName) {
        if (!sourceMapping.containsKey(sourceTableName)) {
            return null;
        }

        TableAndDataSource source = sourceMapping.get(sourceTableName);
        TableAndDataSource target = sourceToTargetMapping.get(sourceTableName);

        return new MigrationPair(source, target);
    }
}
