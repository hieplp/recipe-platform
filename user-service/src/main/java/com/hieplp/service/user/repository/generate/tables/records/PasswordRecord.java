/*
 * This file is generated by jOOQ.
 */
package com.hieplp.service.user.repository.generate.tables.records;


import com.hieplp.service.user.repository.generate.tables.Password;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class PasswordRecord extends UpdatableRecordImpl<PasswordRecord> implements Record7<String, byte[], byte[], String, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached PasswordRecord
     */
    public PasswordRecord() {
        super(Password.PASSWORD);
    }

    /**
     * Create a detached, initialised PasswordRecord
     */
    public PasswordRecord(String userid, byte[] password, byte[] salt, String createdby, LocalDateTime createdat, String modifiedby, LocalDateTime modifiedat) {
        super(Password.PASSWORD);

        setUserid(userid);
        setPassword(password);
        setSalt(salt);
        setCreatedby(createdby);
        setCreatedat(createdat);
        setModifiedby(modifiedby);
        setModifiedat(modifiedat);
    }

    /**
     * Getter for <code>user.password.userId</code>.
     */
    public String getUserid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>user.password.userId</code>.
     */
    public PasswordRecord setUserid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>user.password.password</code>.
     */
    public byte[] getPassword() {
        return (byte[]) get(1);
    }

    /**
     * Setter for <code>user.password.password</code>.
     */
    public PasswordRecord setPassword(byte[] value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>user.password.salt</code>.
     */
    public byte[] getSalt() {
        return (byte[]) get(2);
    }

    /**
     * Setter for <code>user.password.salt</code>.
     */
    public PasswordRecord setSalt(byte[] value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>user.password.createdBy</code>.
     */
    public String getCreatedby() {
        return (String) get(3);
    }

    /**
     * Setter for <code>user.password.createdBy</code>.
     */
    public PasswordRecord setCreatedby(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>user.password.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>user.password.createdAt</code>.
     */
    public PasswordRecord setCreatedat(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>user.password.modifiedBy</code>.
     */
    public String getModifiedby() {
        return (String) get(5);
    }

    /**
     * Setter for <code>user.password.modifiedBy</code>.
     */
    public PasswordRecord setModifiedby(String value) {
        set(5, value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>user.password.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>user.password.modifiedAt</code>.
     */
    public PasswordRecord setModifiedat(LocalDateTime value) {
        set(6, value);
        return this;
    }

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    @Override
    public Row7<String, byte[], byte[], String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, byte[], byte[], String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Password.PASSWORD.USERID;
    }

    @Override
    public Field<byte[]> field2() {
        return Password.PASSWORD.PASSWORD_;
    }

    @Override
    public Field<byte[]> field3() {
        return Password.PASSWORD.SALT;
    }

    @Override
    public Field<String> field4() {
        return Password.PASSWORD.CREATEDBY;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Password.PASSWORD.CREATEDAT;
    }

    @Override
    public Field<String> field6() {
        return Password.PASSWORD.MODIFIEDBY;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Password.PASSWORD.MODIFIEDAT;
    }

    @Override
    public String component1() {
        return getUserid();
    }

    @Override
    public byte[] component2() {
        return getPassword();
    }

    @Override
    public byte[] component3() {
        return getSalt();
    }

    @Override
    public String component4() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedat();
    }

    @Override
    public String component6() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime component7() {
        return getModifiedat();
    }

    @Override
    public String value1() {
        return getUserid();
    }

    @Override
    public byte[] value2() {
        return getPassword();
    }

    @Override
    public byte[] value3() {
        return getSalt();
    }

    @Override
    public String value4() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedat();
    }

    @Override
    public String value6() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime value7() {
        return getModifiedat();
    }

    @Override
    public PasswordRecord value1(String value) {
        setUserid(value);
        return this;
    }

    @Override
    public PasswordRecord value2(byte[] value) {
        setPassword(value);
        return this;
    }

    @Override
    public PasswordRecord value3(byte[] value) {
        setSalt(value);
        return this;
    }

    @Override
    public PasswordRecord value4(String value) {
        setCreatedby(value);
        return this;
    }

    @Override
    public PasswordRecord value5(LocalDateTime value) {
        setCreatedat(value);
        return this;
    }

    @Override
    public PasswordRecord value6(String value) {
        setModifiedby(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public PasswordRecord value7(LocalDateTime value) {
        setModifiedat(value);
        return this;
    }

    @Override
    public PasswordRecord values(String value1, byte[] value2, byte[] value3, String value4, LocalDateTime value5, String value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }
}
