/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate.tables.daos;


import com.hieplp.recipe.user.common.repository.generate.tables.DomainEventEntry;
import com.hieplp.recipe.user.common.repository.generate.tables.records.DomainEventEntryRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DomainEventEntryDao extends DAOImpl<DomainEventEntryRecord, com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry, Long> {

    /**
     * Create a new DomainEventEntryDao without any configuration
     */
    public DomainEventEntryDao() {
        super(DomainEventEntry.DOMAIN_EVENT_ENTRY, com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry.class);
    }

    /**
     * Create a new DomainEventEntryDao with an attached configuration
     */
    public DomainEventEntryDao(Configuration configuration) {
        super(DomainEventEntry.DOMAIN_EVENT_ENTRY, com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry.class, configuration);
    }

    @Override
    public Long getId(com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry object) {
        return object.getGlobalIndex();
    }

    /**
     * Fetch records that have <code>global_index BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfGlobalIndex(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.GLOBAL_INDEX, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>global_index IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByGlobalIndex(Long... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.GLOBAL_INDEX, values);
    }

    /**
     * Fetch a unique record that has <code>global_index = value</code>
     */
    public com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry fetchOneByGlobalIndex(Long value) {
        return fetchOne(DomainEventEntry.DOMAIN_EVENT_ENTRY.GLOBAL_INDEX, value);
    }

    /**
     * Fetch records that have <code>event_identifier BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfEventIdentifier(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.EVENT_IDENTIFIER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>event_identifier IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByEventIdentifier(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.EVENT_IDENTIFIER, values);
    }

    /**
     * Fetch a unique record that has <code>event_identifier = value</code>
     */
    public com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry fetchOneByEventIdentifier(String value) {
        return fetchOne(DomainEventEntry.DOMAIN_EVENT_ENTRY.EVENT_IDENTIFIER, value);
    }

    /**
     * Fetch records that have <code>meta_data BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfMetaData(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.META_DATA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>meta_data IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByMetaData(byte[]... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.META_DATA, values);
    }

    /**
     * Fetch records that have <code>payload BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfPayload(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByPayload(byte[]... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>payload_revision BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfPayloadRevision(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD_REVISION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload_revision IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByPayloadRevision(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD_REVISION, values);
    }

    /**
     * Fetch records that have <code>payload_type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfPayloadType(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD_TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload_type IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByPayloadType(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.PAYLOAD_TYPE, values);
    }

    /**
     * Fetch records that have <code>time_stamp BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfTimeStamp(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.TIME_STAMP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>time_stamp IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByTimeStamp(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.TIME_STAMP, values);
    }

    /**
     * Fetch records that have <code>aggregate_identifier BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfAggregateIdentifier(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.AGGREGATE_IDENTIFIER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>aggregate_identifier IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByAggregateIdentifier(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.AGGREGATE_IDENTIFIER, values);
    }

    /**
     * Fetch records that have <code>sequence_number BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfSequenceNumber(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.SEQUENCE_NUMBER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sequence_number IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchBySequenceNumber(Long... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.SEQUENCE_NUMBER, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(DomainEventEntry.DOMAIN_EVENT_ENTRY.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.DomainEventEntry> fetchByType(String... values) {
        return fetch(DomainEventEntry.DOMAIN_EVENT_ENTRY.TYPE, values);
    }
}
