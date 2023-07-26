/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.auth.common.repository.generate.tables.records;


import com.hieplp.recipe.auth.common.repository.generate.tables.Otp;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OtpRecord extends UpdatableRecordImpl<OtpRecord> implements Record11<String, String, String, Byte, Byte, LocalDateTime, LocalDateTime, LocalDateTime, String, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>authentication.otp.otpId</code>.
     */
    public OtpRecord setOtpid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.otpId</code>.
     */
    public String getOtpid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>authentication.otp.sendTo</code>.
     */
    public OtpRecord setSendto(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.sendTo</code>.
     */
    public String getSendto() {
        return (String) get(1);
    }

    /**
     * Setter for <code>authentication.otp.otpCode</code>.
     */
    public OtpRecord setOtpcode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.otpCode</code>.
     */
    public String getOtpcode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>authentication.otp.type</code>.
     */
    public OtpRecord setType(Byte value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.type</code>.
     */
    public Byte getType() {
        return (Byte) get(3);
    }

    /**
     * Setter for <code>authentication.otp.status</code>.
     */
    public OtpRecord setStatus(Byte value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.status</code>.
     */
    public Byte getStatus() {
        return (Byte) get(4);
    }

    /**
     * Setter for <code>authentication.otp.issuedAt</code>.
     */
    public OtpRecord setIssuedat(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.issuedAt</code>.
     */
    public LocalDateTime getIssuedat() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>authentication.otp.expiredAt</code>.
     */
    public OtpRecord setExpiredat(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.expiredAt</code>.
     */
    public LocalDateTime getExpiredat() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>authentication.otp.createdAt</code>.
     */
    public OtpRecord setCreatedat(LocalDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>authentication.otp.createdBy</code>.
     */
    public OtpRecord setCreatedby(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.createdBy</code>.
     */
    public String getCreatedby() {
        return (String) get(8);
    }

    /**
     * Setter for <code>authentication.otp.modifiedAt</code>.
     */
    public OtpRecord setModifiedat(LocalDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for <code>authentication.otp.modifiedBy</code>.
     */
    public OtpRecord setModifiedby(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>authentication.otp.modifiedBy</code>.
     */
    public String getModifiedby() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<String, String, String, Byte, Byte, LocalDateTime, LocalDateTime, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<String, String, String, Byte, Byte, LocalDateTime, LocalDateTime, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Otp.OTP.OTPID;
    }

    @Override
    public Field<String> field2() {
        return Otp.OTP.SENDTO;
    }

    @Override
    public Field<String> field3() {
        return Otp.OTP.OTPCODE;
    }

    @Override
    public Field<Byte> field4() {
        return Otp.OTP.TYPE;
    }

    @Override
    public Field<Byte> field5() {
        return Otp.OTP.STATUS;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Otp.OTP.ISSUEDAT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Otp.OTP.EXPIREDAT;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return Otp.OTP.CREATEDAT;
    }

    @Override
    public Field<String> field9() {
        return Otp.OTP.CREATEDBY;
    }

    @Override
    public Field<LocalDateTime> field10() {
        return Otp.OTP.MODIFIEDAT;
    }

    @Override
    public Field<String> field11() {
        return Otp.OTP.MODIFIEDBY;
    }

    @Override
    public String component1() {
        return getOtpid();
    }

    @Override
    public String component2() {
        return getSendto();
    }

    @Override
    public String component3() {
        return getOtpcode();
    }

    @Override
    public Byte component4() {
        return getType();
    }

    @Override
    public Byte component5() {
        return getStatus();
    }

    @Override
    public LocalDateTime component6() {
        return getIssuedat();
    }

    @Override
    public LocalDateTime component7() {
        return getExpiredat();
    }

    @Override
    public LocalDateTime component8() {
        return getCreatedat();
    }

    @Override
    public String component9() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime component10() {
        return getModifiedat();
    }

    @Override
    public String component11() {
        return getModifiedby();
    }

    @Override
    public String value1() {
        return getOtpid();
    }

    @Override
    public String value2() {
        return getSendto();
    }

    @Override
    public String value3() {
        return getOtpcode();
    }

    @Override
    public Byte value4() {
        return getType();
    }

    @Override
    public Byte value5() {
        return getStatus();
    }

    @Override
    public LocalDateTime value6() {
        return getIssuedat();
    }

    @Override
    public LocalDateTime value7() {
        return getExpiredat();
    }

    @Override
    public LocalDateTime value8() {
        return getCreatedat();
    }

    @Override
    public String value9() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime value10() {
        return getModifiedat();
    }

    @Override
    public String value11() {
        return getModifiedby();
    }

    @Override
    public OtpRecord value1(String value) {
        setOtpid(value);
        return this;
    }

    @Override
    public OtpRecord value2(String value) {
        setSendto(value);
        return this;
    }

    @Override
    public OtpRecord value3(String value) {
        setOtpcode(value);
        return this;
    }

    @Override
    public OtpRecord value4(Byte value) {
        setType(value);
        return this;
    }

    @Override
    public OtpRecord value5(Byte value) {
        setStatus(value);
        return this;
    }

    @Override
    public OtpRecord value6(LocalDateTime value) {
        setIssuedat(value);
        return this;
    }

    @Override
    public OtpRecord value7(LocalDateTime value) {
        setExpiredat(value);
        return this;
    }

    @Override
    public OtpRecord value8(LocalDateTime value) {
        setCreatedat(value);
        return this;
    }

    @Override
    public OtpRecord value9(String value) {
        setCreatedby(value);
        return this;
    }

    @Override
    public OtpRecord value10(LocalDateTime value) {
        setModifiedat(value);
        return this;
    }

    @Override
    public OtpRecord value11(String value) {
        setModifiedby(value);
        return this;
    }

    @Override
    public OtpRecord values(String value1, String value2, String value3, Byte value4, Byte value5, LocalDateTime value6, LocalDateTime value7, LocalDateTime value8, String value9, LocalDateTime value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OtpRecord
     */
    public OtpRecord() {
        super(Otp.OTP);
    }

    /**
     * Create a detached, initialised OtpRecord
     */
    public OtpRecord(String otpid, String sendto, String otpcode, Byte type, Byte status, LocalDateTime issuedat, LocalDateTime expiredat, LocalDateTime createdat, String createdby, LocalDateTime modifiedat, String modifiedby) {
        super(Otp.OTP);

        setOtpid(otpid);
        setSendto(sendto);
        setOtpcode(otpcode);
        setType(type);
        setStatus(status);
        setIssuedat(issuedat);
        setExpiredat(expiredat);
        setCreatedat(createdat);
        setCreatedby(createdby);
        setModifiedat(modifiedat);
        setModifiedby(modifiedby);
    }
}
