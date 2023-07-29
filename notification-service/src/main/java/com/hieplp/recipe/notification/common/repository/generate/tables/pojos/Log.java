/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.notification.common.repository.generate.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private String logid;
    private String action;
    private Byte sendvia;
    private String subject;
    private String content;
    private String sendto;
    private Byte status;
    private String referenceid;
    private String createdby;
    private LocalDateTime createdat;
    private String modifiedby;
    private LocalDateTime modifiedat;

    public Log() {
    }

    public Log(Log value) {
        this.logid = value.logid;
        this.action = value.action;
        this.sendvia = value.sendvia;
        this.subject = value.subject;
        this.content = value.content;
        this.sendto = value.sendto;
        this.status = value.status;
        this.referenceid = value.referenceid;
        this.createdby = value.createdby;
        this.createdat = value.createdat;
        this.modifiedby = value.modifiedby;
        this.modifiedat = value.modifiedat;
    }

    public Log(
            String logid,
            String action,
            Byte sendvia,
            String subject,
            String content,
            String sendto,
            Byte status,
            String referenceid,
            String createdby,
            LocalDateTime createdat,
            String modifiedby,
            LocalDateTime modifiedat
    ) {
        this.logid = logid;
        this.action = action;
        this.sendvia = sendvia;
        this.subject = subject;
        this.content = content;
        this.sendto = sendto;
        this.status = status;
        this.referenceid = referenceid;
        this.createdby = createdby;
        this.createdat = createdat;
        this.modifiedby = modifiedby;
        this.modifiedat = modifiedat;
    }

    /**
     * Getter for <code>notification.log.logId</code>.
     */
    public String getLogid() {
        return this.logid;
    }

    /**
     * Setter for <code>notification.log.logId</code>.
     */
    public Log setLogid(String logid) {
        this.logid = logid;
        return this;
    }

    /**
     * Getter for <code>notification.log.action</code>.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Setter for <code>notification.log.action</code>.
     */
    public Log setAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * Getter for <code>notification.log.sendVia</code>.
     */
    public Byte getSendvia() {
        return this.sendvia;
    }

    /**
     * Setter for <code>notification.log.sendVia</code>.
     */
    public Log setSendvia(Byte sendvia) {
        this.sendvia = sendvia;
        return this;
    }

    /**
     * Getter for <code>notification.log.subject</code>.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Setter for <code>notification.log.subject</code>.
     */
    public Log setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Getter for <code>notification.log.content</code>.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Setter for <code>notification.log.content</code>.
     */
    public Log setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Getter for <code>notification.log.sendTo</code>.
     */
    public String getSendto() {
        return this.sendto;
    }

    /**
     * Setter for <code>notification.log.sendTo</code>.
     */
    public Log setSendto(String sendto) {
        this.sendto = sendto;
        return this;
    }

    /**
     * Getter for <code>notification.log.status</code>.
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>notification.log.status</code>.
     */
    public Log setStatus(Byte status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>notification.log.referenceId</code>.
     */
    public String getReferenceid() {
        return this.referenceid;
    }

    /**
     * Setter for <code>notification.log.referenceId</code>.
     */
    public Log setReferenceid(String referenceid) {
        this.referenceid = referenceid;
        return this;
    }

    /**
     * Getter for <code>notification.log.createdBy</code>.
     */
    public String getCreatedby() {
        return this.createdby;
    }

    /**
     * Setter for <code>notification.log.createdBy</code>.
     */
    public Log setCreatedby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    /**
     * Getter for <code>notification.log.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return this.createdat;
    }

    /**
     * Setter for <code>notification.log.createdAt</code>.
     */
    public Log setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
        return this;
    }

    /**
     * Getter for <code>notification.log.modifiedBy</code>.
     */
    public String getModifiedby() {
        return this.modifiedby;
    }

    /**
     * Setter for <code>notification.log.modifiedBy</code>.
     */
    public Log setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
        return this;
    }

    /**
     * Getter for <code>notification.log.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return this.modifiedat;
    }

    /**
     * Setter for <code>notification.log.modifiedAt</code>.
     */
    public Log setModifiedat(LocalDateTime modifiedat) {
        this.modifiedat = modifiedat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Log (");

        sb.append(logid);
        sb.append(", ").append(action);
        sb.append(", ").append(sendvia);
        sb.append(", ").append(subject);
        sb.append(", ").append(content);
        sb.append(", ").append(sendto);
        sb.append(", ").append(status);
        sb.append(", ").append(referenceid);
        sb.append(", ").append(createdby);
        sb.append(", ").append(createdat);
        sb.append(", ").append(modifiedby);
        sb.append(", ").append(modifiedat);

        sb.append(")");
        return sb.toString();
    }
}