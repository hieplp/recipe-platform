/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.auth.common.repository.generate.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Otphistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String        otphistoryid;
    private String        otpid;
    private String        otpcode;
    private Byte          status;
    private Byte          type;
    private String        createdby;
    private LocalDateTime createdat;
    private String        modifiedby;
    private LocalDateTime modifiedat;

    public Otphistory() {}

    public Otphistory(Otphistory value) {
        this.otphistoryid = value.otphistoryid;
        this.otpid = value.otpid;
        this.otpcode = value.otpcode;
        this.status = value.status;
        this.type = value.type;
        this.createdby = value.createdby;
        this.createdat = value.createdat;
        this.modifiedby = value.modifiedby;
        this.modifiedat = value.modifiedat;
    }

    public Otphistory(
        String        otphistoryid,
        String        otpid,
        String        otpcode,
        Byte          status,
        Byte          type,
        String        createdby,
        LocalDateTime createdat,
        String        modifiedby,
        LocalDateTime modifiedat
    ) {
        this.otphistoryid = otphistoryid;
        this.otpid = otpid;
        this.otpcode = otpcode;
        this.status = status;
        this.type = type;
        this.createdby = createdby;
        this.createdat = createdat;
        this.modifiedby = modifiedby;
        this.modifiedat = modifiedat;
    }

    /**
     * Getter for <code>authentication.otpHistory.otpHistoryId</code>.
     */
    public String getOtphistoryid() {
        return this.otphistoryid;
    }

    /**
     * Setter for <code>authentication.otpHistory.otpHistoryId</code>.
     */
    public Otphistory setOtphistoryid(String otphistoryid) {
        this.otphistoryid = otphistoryid;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.otpId</code>.
     */
    public String getOtpid() {
        return this.otpid;
    }

    /**
     * Setter for <code>authentication.otpHistory.otpId</code>.
     */
    public Otphistory setOtpid(String otpid) {
        this.otpid = otpid;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.otpCode</code>.
     */
    public String getOtpcode() {
        return this.otpcode;
    }

    /**
     * Setter for <code>authentication.otpHistory.otpCode</code>.
     */
    public Otphistory setOtpcode(String otpcode) {
        this.otpcode = otpcode;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.status</code>.
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>authentication.otpHistory.status</code>.
     */
    public Otphistory setStatus(Byte status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.type</code>.
     */
    public Byte getType() {
        return this.type;
    }

    /**
     * Setter for <code>authentication.otpHistory.type</code>.
     */
    public Otphistory setType(Byte type) {
        this.type = type;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.createdBy</code>.
     */
    public String getCreatedby() {
        return this.createdby;
    }

    /**
     * Setter for <code>authentication.otpHistory.createdBy</code>.
     */
    public Otphistory setCreatedby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return this.createdat;
    }

    /**
     * Setter for <code>authentication.otpHistory.createdAt</code>.
     */
    public Otphistory setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.modifiedBy</code>.
     */
    public String getModifiedby() {
        return this.modifiedby;
    }

    /**
     * Setter for <code>authentication.otpHistory.modifiedBy</code>.
     */
    public Otphistory setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
        return this;
    }

    /**
     * Getter for <code>authentication.otpHistory.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return this.modifiedat;
    }

    /**
     * Setter for <code>authentication.otpHistory.modifiedAt</code>.
     */
    public Otphistory setModifiedat(LocalDateTime modifiedat) {
        this.modifiedat = modifiedat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Otphistory (");

        sb.append(otphistoryid);
        sb.append(", ").append(otpid);
        sb.append(", ").append(otpcode);
        sb.append(", ").append(status);
        sb.append(", ").append(type);
        sb.append(", ").append(createdby);
        sb.append(", ").append(createdat);
        sb.append(", ").append(modifiedby);
        sb.append(", ").append(modifiedat);

        sb.append(")");
        return sb.toString();
    }
}
