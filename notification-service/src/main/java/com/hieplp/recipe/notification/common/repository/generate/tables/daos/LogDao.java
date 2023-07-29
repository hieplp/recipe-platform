/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.notification.common.repository.generate.tables.daos;


import com.hieplp.recipe.notification.common.repository.generate.tables.Log;
import com.hieplp.recipe.notification.common.repository.generate.tables.records.LogRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class LogDao extends DAOImpl<LogRecord, com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log, String> {

    /**
     * Create a new LogDao without any configuration
     */
    public LogDao() {
        super(Log.LOG, com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log.class);
    }

    /**
     * Create a new LogDao with an attached configuration
     */
    public LogDao(Configuration configuration) {
        super(Log.LOG, com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log.class, configuration);
    }

    @Override
    public String getId(com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log object) {
        return object.getLogid();
    }

    /**
     * Fetch records that have <code>logId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfLogid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.LOGID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>logId IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByLogid(String... values) {
        return fetch(Log.LOG.LOGID, values);
    }

    /**
     * Fetch a unique record that has <code>logId = value</code>
     */
    public com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log fetchOneByLogid(String value) {
        return fetchOne(Log.LOG.LOGID, value);
    }

    /**
     * Fetch records that have <code>action BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfAction(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.ACTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>action IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByAction(String... values) {
        return fetch(Log.LOG.ACTION, values);
    }

    /**
     * Fetch records that have <code>sendVia BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfSendvia(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Log.LOG.SENDVIA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sendVia IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchBySendvia(Byte... values) {
        return fetch(Log.LOG.SENDVIA, values);
    }

    /**
     * Fetch records that have <code>subject BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfSubject(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.SUBJECT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>subject IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchBySubject(String... values) {
        return fetch(Log.LOG.SUBJECT, values);
    }

    /**
     * Fetch records that have <code>content BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfContent(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.CONTENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>content IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByContent(String... values) {
        return fetch(Log.LOG.CONTENT, values);
    }

    /**
     * Fetch records that have <code>sendTo BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfSendto(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.SENDTO, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sendTo IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchBySendto(String... values) {
        return fetch(Log.LOG.SENDTO, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfStatus(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Log.LOG.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByStatus(Byte... values) {
        return fetch(Log.LOG.STATUS, values);
    }

    /**
     * Fetch records that have <code>referenceId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfReferenceid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.REFERENCEID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>referenceId IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByReferenceid(String... values) {
        return fetch(Log.LOG.REFERENCEID, values);
    }

    /**
     * Fetch records that have <code>createdBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfCreatedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.CREATEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdBy IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByCreatedby(String... values) {
        return fetch(Log.LOG.CREATEDBY, values);
    }

    /**
     * Fetch records that have <code>createdAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfCreatedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Log.LOG.CREATEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdAt IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByCreatedat(LocalDateTime... values) {
        return fetch(Log.LOG.CREATEDAT, values);
    }

    /**
     * Fetch records that have <code>modifiedBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfModifiedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Log.LOG.MODIFIEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedBy IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByModifiedby(String... values) {
        return fetch(Log.LOG.MODIFIEDBY, values);
    }

    /**
     * Fetch records that have <code>modifiedAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchRangeOfModifiedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Log.LOG.MODIFIEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedAt IN (values)</code>
     */
    public List<com.hieplp.recipe.notification.common.repository.generate.tables.pojos.Log> fetchByModifiedat(LocalDateTime... values) {
        return fetch(Log.LOG.MODIFIEDAT, values);
    }
}
