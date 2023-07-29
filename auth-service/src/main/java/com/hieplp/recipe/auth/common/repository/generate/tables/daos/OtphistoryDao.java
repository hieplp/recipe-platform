/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.auth.common.repository.generate.tables.daos;


import com.hieplp.recipe.auth.common.repository.generate.tables.Otphistory;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtphistoryRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class OtphistoryDao extends DAOImpl<OtphistoryRecord, com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory, String> {

    /**
     * Create a new OtphistoryDao without any configuration
     */
    public OtphistoryDao() {
        super(Otphistory.OTPHISTORY, com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory.class);
    }

    /**
     * Create a new OtphistoryDao with an attached configuration
     */
    public OtphistoryDao(Configuration configuration) {
        super(Otphistory.OTPHISTORY, com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory.class, configuration);
    }

    @Override
    public String getId(com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory object) {
        return object.getOtphistoryid();
    }

    /**
     * Fetch records that have <code>otpHistoryId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfOtphistoryid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.OTPHISTORYID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>otpHistoryId IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByOtphistoryid(String... values) {
        return fetch(Otphistory.OTPHISTORY.OTPHISTORYID, values);
    }

    /**
     * Fetch a unique record that has <code>otpHistoryId = value</code>
     */
    public com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory fetchOneByOtphistoryid(String value) {
        return fetchOne(Otphistory.OTPHISTORY.OTPHISTORYID, value);
    }

    /**
     * Fetch records that have <code>otpId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfOtpid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.OTPID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>otpId IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByOtpid(String... values) {
        return fetch(Otphistory.OTPHISTORY.OTPID, values);
    }

    /**
     * Fetch records that have <code>otpCode BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfOtpcode(String lowerInclusive, String upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.OTPCODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>otpCode IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByOtpcode(String... values) {
        return fetch(Otphistory.OTPHISTORY.OTPCODE, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfStatus(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByStatus(Byte... values) {
        return fetch(Otphistory.OTPHISTORY.STATUS, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfType(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByType(Byte... values) {
        return fetch(Otphistory.OTPHISTORY.TYPE, values);
    }

    /**
     * Fetch records that have <code>createdBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfCreatedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.CREATEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdBy IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByCreatedby(String... values) {
        return fetch(Otphistory.OTPHISTORY.CREATEDBY, values);
    }

    /**
     * Fetch records that have <code>createdAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfCreatedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.CREATEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdAt IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByCreatedat(LocalDateTime... values) {
        return fetch(Otphistory.OTPHISTORY.CREATEDAT, values);
    }

    /**
     * Fetch records that have <code>modifiedBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfModifiedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.MODIFIEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedBy IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByModifiedby(String... values) {
        return fetch(Otphistory.OTPHISTORY.MODIFIEDBY, values);
    }

    /**
     * Fetch records that have <code>modifiedAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchRangeOfModifiedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Otphistory.OTPHISTORY.MODIFIEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedAt IN (values)</code>
     */
    public List<com.hieplp.recipe.auth.common.repository.generate.tables.pojos.Otphistory> fetchByModifiedat(LocalDateTime... values) {
        return fetch(Otphistory.OTPHISTORY.MODIFIEDAT, values);
    }
}
