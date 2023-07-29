package com.hieplp.recipe.common.jooq.base;

import com.hieplp.recipe.common.jooq.exception.ExecuteException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.jooq.exception.QueryException;
import com.hieplp.recipe.common.jooq.util.TableUtil;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class BaseRepoImpl implements BaseRepo {

    protected final DSLContext context;

    public BaseRepoImpl(DSLContext context) {
        this.context = context;
    }

    @Transactional
    @Override
    public int save(Record record) {
        try {
            log.info("Save record");
            return context.insertInto(TableUtil.getTable(record))
                    .set(record)
                    .execute();
        } catch (Exception e) {
            log.error("Error when save new record: {}", e.getMessage());
            throw new ExecuteException(e.getMessage());
        }
    }

    @Override
    public int delete(Record record) {
        try {
            var table = TableUtil.getTable(record);
            log.info("Delete record from table: {}", table);
            return context.deleteFrom(table)
                    .where(TableUtil.equalKey(table, record))
                    .execute();
        } catch (Exception e) {
            log.error("Error when delete record: {}", e.getMessage());
            throw new ExecuteException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public int updateNotNull(Record record) {
        try {
            Arrays.stream(record.fields()).forEach(field -> record.changed(field, record.getValue(field) != null));
            Table<?> table = TableUtil.getTable(record);
            return context.update(table)
                    .set(record)
                    .where(TableUtil.equalKey(table, record))
                    .execute();
        } catch (Exception e) {
            log.error("Error when update not null record: {}", e.getMessage());
            throw new ExecuteException(e.getMessage());
        }
    }

    @Override
    public void transaction(TransactionHandler handler) {
        try {
            context.transaction(configuration -> handler.handle(DSL.using(configuration)));
        } catch (Exception e) {
            log.error("Error when execute transaction: {}", e.getMessage());
            throw new ExecuteException(e.getMessage());
        }
    }

    @Override
    public <R extends Record> R fetchOneNotNull(Table<R> table, Condition condition) {
        try {
            return context.fetch(table, condition).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type) {
        try {
            return context.fetch(table, condition).into(type).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> Optional<T> fetchOne(Table<?> table, Condition condition, Class<? extends T> type) {
        try {
            return Optional.of(context.fetch(table, condition).into(type).get(0));
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public boolean fetchExist(Table<?> table, Condition condition) {
        try {
            return context.fetchExists(table, condition);
        } catch (Exception e) {
            log.info("Error when fetch exist record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }
}
