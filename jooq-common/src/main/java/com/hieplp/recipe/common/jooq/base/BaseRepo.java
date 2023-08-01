package com.hieplp.recipe.common.jooq.base;

import com.hieplp.recipe.common.jooq.exception.ExecuteException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import java.util.Optional;

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
     * Delete record from database
     *
     * @param record record to delete
     */
    int delete(Record record);

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


    /**
     * Fetch one record from database. If record is null, throw exception
     *
     * @param table     table
     * @param condition queried condition
     * @param type      type of return object
     * @param <T>       type of return object
     * @return queried object
     */
    <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type);

    /**
     * Fetch one record from database. If record is null, return empty optional
     *
     * @param table     table
     * @param condition queried condition
     * @param type      type of return object
     * @param <T>       type of return object
     * @return queried object
     */
    <T> Optional<T> fetchOne(Table<?> table, Condition condition, Class<? extends T> type);

    /**
     * Fetch one record from database. If record is null, return empty optional
     *
     * @param table     table
     * @param condition queried condition
     * @param type      type of return object
     * @param fields    fields to fetch
     * @param <T>       type of return object
     * @return queried object
     */
    <T> Optional<T> fetchOne(Table<?> table, Condition condition, Class<? extends T> type, Field<?>... fields);

    /**
     * Check if record exist in table
     *
     * @param table     table to fetch
     * @param condition condition to filter
     * @return true if record exist, false otherwise
     */
    boolean fetchExist(Table<?> table, Condition condition);
}
