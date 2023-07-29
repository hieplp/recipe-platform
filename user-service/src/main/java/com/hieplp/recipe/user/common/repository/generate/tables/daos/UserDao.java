/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate.tables.daos;


import com.hieplp.recipe.user.common.repository.generate.tables.User;
import com.hieplp.recipe.user.common.repository.generate.tables.records.UserRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserDao extends DAOImpl<UserRecord, com.hieplp.recipe.user.common.repository.generate.tables.pojos.User, String> {

    /**
     * Create a new UserDao without any configuration
     */
    public UserDao() {
        super(User.USER_, com.hieplp.recipe.user.common.repository.generate.tables.pojos.User.class);
    }

    /**
     * Create a new UserDao with an attached configuration
     */
    public UserDao(Configuration configuration) {
        super(User.USER_, com.hieplp.recipe.user.common.repository.generate.tables.pojos.User.class, configuration);
    }

    @Override
    public String getId(com.hieplp.recipe.user.common.repository.generate.tables.pojos.User object) {
        return object.getUserid();
    }

    /**
     * Fetch records that have <code>userId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfUserid(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.USERID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>userId IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByUserid(String... values) {
        return fetch(User.USER_.USERID, values);
    }

    /**
     * Fetch a unique record that has <code>userId = value</code>
     */
    public com.hieplp.recipe.user.common.repository.generate.tables.pojos.User fetchOneByUserid(String value) {
        return fetchOne(User.USER_.USERID, value);
    }

    /**
     * Fetch records that have <code>username BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfUsername(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.USERNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByUsername(String... values) {
        return fetch(User.USER_.USERNAME, values);
    }

    /**
     * Fetch a unique record that has <code>username = value</code>
     */
    public com.hieplp.recipe.user.common.repository.generate.tables.pojos.User fetchOneByUsername(String value) {
        return fetchOne(User.USER_.USERNAME, value);
    }

    /**
     * Fetch records that have <code>fullName BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfFullname(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.FULLNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fullName IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByFullname(String... values) {
        return fetch(User.USER_.FULLNAME, values);
    }

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByEmail(String... values) {
        return fetch(User.USER_.EMAIL, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfStatus(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(User.USER_.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByStatus(Byte... values) {
        return fetch(User.USER_.STATUS, values);
    }

    /**
     * Fetch records that have <code>createdBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfCreatedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.CREATEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdBy IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByCreatedby(String... values) {
        return fetch(User.USER_.CREATEDBY, values);
    }

    /**
     * Fetch records that have <code>createdAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfCreatedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(User.USER_.CREATEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdAt IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByCreatedat(LocalDateTime... values) {
        return fetch(User.USER_.CREATEDAT, values);
    }

    /**
     * Fetch records that have <code>modifiedBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfModifiedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(User.USER_.MODIFIEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedBy IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByModifiedby(String... values) {
        return fetch(User.USER_.MODIFIEDBY, values);
    }

    /**
     * Fetch records that have <code>modifiedAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchRangeOfModifiedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(User.USER_.MODIFIEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedAt IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.User> fetchByModifiedat(LocalDateTime... values) {
        return fetch(User.USER_.MODIFIEDAT, values);
    }
}