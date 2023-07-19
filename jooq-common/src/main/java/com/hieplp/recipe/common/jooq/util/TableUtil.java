package com.hieplp.recipe.common.jooq.util;

import org.jooq.*;
import org.jooq.impl.DSL;

public class TableUtil {


    /**
     * Get table of record
     *
     * @param record table record
     * @return table of record
     */
    public static Table<?> getTable(Record record) {
        return ((TableRecord<?>) record).getTable();
    }

    /**
     * Generate condition base on primary key of table
     *
     * @param table  table
     * @param record record of table
     * @return condition of equal key
     */
    public static Condition equalKey(Table<?> table, Record record) {
        var condition = DSL.noCondition();
        if (table.getPrimaryKey() == null) {
            return condition;
        }

        for (TableField field : table.getPrimaryKey().getFields()) {
            var fieldValue = field.getDataType().convert(record.getValue(field.getName()));
            condition = condition.and(field.eq(fieldValue));
        }

        return condition;
    }
}
