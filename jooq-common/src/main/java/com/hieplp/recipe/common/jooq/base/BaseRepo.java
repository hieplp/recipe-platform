package com.hieplp.recipe.common.jooq.base;

import com.hieplp.recipe.common.jooq.exception.ExecuteException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.Table;

public interface BaseRepo {
    /**
     * Save record into database
     *
     * @param record record to save
     * @return number of record affected
     * @throws ExecuteException when save failed
     */
    int save(Record record);

    /**
     * Update record into database. Only update not null field
     *
     * @param record record to update
     * @return number of record affected
     * @throws ExecuteException when update failed
     */
    int updateNotNull(Record record);

    /**
     * Execute transaction
     *
     * @param handler transaction handler
     */
    void transaction(TransactionHandler handler);

    /**
     * Fetch record from table with condition. If record not found, throw exception
     *
     * @param table     table to fetch
     * @param condition condition to filter
     * @return record matched condition
     * @throws NotFoundException when record not found
     * @throws ExecuteException  when fetch failed
     */
    <R extends Record> R fetchOneNotNull(Table<R> table, Condition condition);
}
