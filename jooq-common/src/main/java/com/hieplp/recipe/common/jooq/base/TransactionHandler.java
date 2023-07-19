package com.hieplp.recipe.common.jooq.base;

import org.jooq.DSLContext;

@FunctionalInterface
public interface TransactionHandler {
    void handle(DSLContext context);
}
