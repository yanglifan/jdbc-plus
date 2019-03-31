package com.github.yanglifan.jdbcplus.migration;

public class DefaultMigrationConfigurationProvider implements MigrationConfigurationProvider {
    private volatile MigrationStage currentStage;

    @Override
    public MigrationStage stage() {
        return currentStage;
    }

    public void setStage(MigrationStage stage) {
        this.currentStage = stage;
    }
}
