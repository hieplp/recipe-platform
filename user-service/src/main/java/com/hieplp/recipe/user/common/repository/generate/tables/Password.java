/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate.tables;


import com.hieplp.recipe.user.common.repository.generate.Keys;
import com.hieplp.recipe.user.common.repository.generate.User;
import com.hieplp.recipe.user.common.repository.generate.tables.records.PasswordRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Password extends TableImpl<PasswordRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>user.password</code>
     */
    public static final Password PASSWORD = new Password();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PasswordRecord> getRecordType() {
        return PasswordRecord.class;
    }

    /**
     * The column <code>user.password.userId</code>.
     */
    public final TableField<PasswordRecord, String> USERID = createField(DSL.name("userId"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>user.password.password</code>.
     */
    public final TableField<PasswordRecord, byte[]> PASSWORD_ = createField(DSL.name("password"), SQLDataType.BINARY(64).defaultValue(DSL.inline("NULL", SQLDataType.BINARY)), this, "");

    /**
     * The column <code>user.password.salt</code>.
     */
    public final TableField<PasswordRecord, byte[]> SALT = createField(DSL.name("salt"), SQLDataType.BINARY(64).defaultValue(DSL.inline("NULL", SQLDataType.BINARY)), this, "");

    /**
     * The column <code>user.password.createdBy</code>.
     */
    public final TableField<PasswordRecord, String> CREATEDBY = createField(DSL.name("createdBy"), SQLDataType.VARCHAR(20).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>user.password.createdAt</code>.
     */
    public final TableField<PasswordRecord, LocalDateTime> CREATEDAT = createField(DSL.name("createdAt"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.inline("NULL", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>user.password.modifiedBy</code>.
     */
    public final TableField<PasswordRecord, String> MODIFIEDBY = createField(DSL.name("modifiedBy"), SQLDataType.VARCHAR(20).defaultValue(DSL.inline("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>user.password.modifiedAt</code>.
     */
    public final TableField<PasswordRecord, LocalDateTime> MODIFIEDAT = createField(DSL.name("modifiedAt"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.inline("NULL", SQLDataType.LOCALDATETIME)), this, "");

    private Password(Name alias, Table<PasswordRecord> aliased) {
        this(alias, aliased, null);
    }

    private Password(Name alias, Table<PasswordRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>user.password</code> table reference
     */
    public Password(String alias) {
        this(DSL.name(alias), PASSWORD);
    }

    /**
     * Create an aliased <code>user.password</code> table reference
     */
    public Password(Name alias) {
        this(alias, PASSWORD);
    }

    /**
     * Create a <code>user.password</code> table reference
     */
    public Password() {
        this(DSL.name("password"), null);
    }

    public <O extends Record> Password(Table<O> child, ForeignKey<O, PasswordRecord> key) {
        super(child, key, PASSWORD);
    }

    @Override
    public Schema getSchema() {
        return User.USER;
    }

    @Override
    public UniqueKey<PasswordRecord> getPrimaryKey() {
        return Keys.KEY_PASSWORD_PRIMARY;
    }

    @Override
    public List<UniqueKey<PasswordRecord>> getKeys() {
        return Arrays.<UniqueKey<PasswordRecord>>asList(Keys.KEY_PASSWORD_PRIMARY);
    }

    @Override
    public Password as(String alias) {
        return new Password(DSL.name(alias), this);
    }

    @Override
    public Password as(Name alias) {
        return new Password(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Password rename(String name) {
        return new Password(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Password rename(Name name) {
        return new Password(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, byte[], byte[], String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
