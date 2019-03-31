package com.github.yanglifan.jdbcplus.migration;

public abstract class MigrationProxyBuilder<S, R> {
    protected ProxyFunction<S, R> proxyFunction;

    protected MigrationProxyBuilder(ProxyFunction<S, R> proxyFunction) {
        this.proxyFunction = proxyFunction;
    }

    protected R buildProxy(S s, Object... args) {
        try {
            return proxyFunction.handle(s, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
