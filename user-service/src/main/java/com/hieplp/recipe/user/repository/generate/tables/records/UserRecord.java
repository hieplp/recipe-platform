/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.repository.generate.tables.records;


import com.hieplp.recipe.user.repository.generate.tables.User;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record9<String, String, String, String, Byte, String, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>user.user.userId</code>.
     */
    public UserRecord setUserid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>user.user.userId</code>.
     */
    public String getUserid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>user.user.username</code>.
     */
    public UserRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>user.user.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>user.user.fullName</code>.
     */
    public UserRecord setFullname(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>user.user.fullName</code>.
     */
    public String getFullname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>user.user.email</code>.
     */
    public UserRecord setEmail(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>user.user.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>user.user.status</code>.
     */
    public UserRecord setStatus(Byte value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>user.user.status</code>.
     */
    public Byte getStatus() {
        return (Byte) get(4);
    }

    /**
     * Setter for <code>user.user.createdBy</code>.
     */
    public UserRecord setCreatedby(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>user.user.createdBy</code>.
     */
    public String getCreatedby() {
        return (String) get(5);
    }

    /**
     * Setter for <code>user.user.createdAt</code>.
     */
    public UserRecord setCreatedat(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>user.user.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>user.user.modifiedBy</code>.
     */
    public UserRecord setModifiedby(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>user.user.modifiedBy</code>.
     */
    public String getModifiedby() {
        return (String) get(7);
    }

    /**
     * Setter for <code>user.user.modifiedAt</code>.
     */
    public UserRecord setModifiedat(LocalDateTime value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>user.user.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return (LocalDateTime) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<String, String, String, String, Byte, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<String, String, String, String, Byte, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return User.USER_.USERID;
    }

    @Override
    public Field<String> field2() {
        return User.USER_.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return User.USER_.FULLNAME;
    }

    @Override
    public Field<String> field4() {
        return User.USER_.EMAIL;
    }

    @Override
    public Field<Byte> field5() {
        return User.USER_.STATUS;
    }

    @Override
    public Field<String> field6() {
        return User.USER_.CREATEDBY;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return User.USER_.CREATEDAT;
    }

    @Override
    public Field<String> field8() {
        return User.USER_.MODIFIEDBY;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return User.USER_.MODIFIEDAT;
    }

    @Override
    public String component1() {
        return getUserid();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getFullname();
    }

    @Override
    public String component4() {
        return getEmail();
    }

    @Override
    public Byte component5() {
        return getStatus();
    }

    @Override
    public String component6() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime component7() {
        return getCreatedat();
    }

    @Override
    public String component8() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime component9() {
        return getModifiedat();
    }

    @Override
    public String value1() {
        return getUserid();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getFullname();
    }

    @Override
    public String value4() {
        return getEmail();
    }

    @Override
    public Byte value5() {
        return getStatus();
    }

    @Override
    public String value6() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime value7() {
        return getCreatedat();
    }

    @Override
    public String value8() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime value9() {
        return getModifiedat();
    }

    @Override
    public UserRecord value1(String value) {
        setUserid(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UserRecord value3(String value) {
        setFullname(value);
        return this;
    }

    @Override
    public UserRecord value4(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UserRecord value5(Byte value) {
        setStatus(value);
        return this;
    }

    @Override
    public UserRecord value6(String value) {
        setCreatedby(value);
        return this;
    }

    @Override
    public UserRecord value7(LocalDateTime value) {
        setCreatedat(value);
        return this;
    }

    @Override
    public UserRecord value8(String value) {
        setModifiedby(value);
        return this;
    }

    @Override
    public UserRecord value9(LocalDateTime value) {
        setModifiedat(value);
        return this;
    }

    @Override
    public UserRecord values(String value1, String value2, String value3, String value4, Byte value5, String value6, LocalDateTime value7, String value8, LocalDateTime value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER_);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(String userid, String username, String fullname, String email, Byte status, String createdby, LocalDateTime createdat, String modifiedby, LocalDateTime modifiedat) {
        super(User.USER_);

        setUserid(userid);
        setUsername(username);
        setFullname(fullname);
        setEmail(email);
        setStatus(status);
        setCreatedby(createdby);
        setCreatedat(createdat);
        setModifiedby(modifiedby);
        setModifiedat(modifiedat);
    }
}
