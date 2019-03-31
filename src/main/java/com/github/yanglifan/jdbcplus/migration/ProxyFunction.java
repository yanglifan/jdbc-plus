package com.github.yanglifan.jdbcplus.migration;

/**
 * @author Yang Lifan
 */
public interface ProxyFunction<S, R> {
    R handle(S s, Object... args) throws Exception;
}
