package com.github.yanglifan.jdbcplus.migration;

public class MigrationPair {
    private TableAndDataSource source;
    private TableAndDataSource target;

    public MigrationPair(TableAndDataSource source, TableAndDataSource target) {
        this.source = source;
        this.target = target;
    }

    public TableAndDataSource getSource() {
        return source;
    }

    public TableAndDataSource getTarget() {
        return target;
    }
}
