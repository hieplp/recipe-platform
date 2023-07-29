/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate.tables.daos;


import com.hieplp.recipe.user.common.repository.generate.tables.SnapshotEventEntry;
import com.hieplp.recipe.user.common.repository.generate.tables.records.SnapshotEventEntryRecord;
import org.jooq.Configuration;
import org.jooq.Record3;
import org.jooq.impl.DAOImpl;

import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SnapshotEventEntryDao extends DAOImpl<SnapshotEventEntryRecord, com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry, Record3<String, Long, String>> {

    /**
     * Create a new SnapshotEventEntryDao without any configuration
     */
    public SnapshotEventEntryDao() {
        super(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY, com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry.class);
    }

    /**
     * Create a new SnapshotEventEntryDao with an attached configuration
     */
    public SnapshotEventEntryDao(Configuration configuration) {
        super(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY, com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry.class, configuration);
    }

    @Override
    public Record3<String, Long, String> getId(com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry object) {
        return compositeKeyRecord(object.getAggregateIdentifier(), object.getSequenceNumber(), object.getType());
    }

    /**
     * Fetch records that have <code>aggregate_identifier BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfAggregateIdentifier(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.AGGREGATE_IDENTIFIER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>aggregate_identifier IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByAggregateIdentifier(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.AGGREGATE_IDENTIFIER, values);
    }

    /**
     * Fetch records that have <code>sequence_number BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfSequenceNumber(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.SEQUENCE_NUMBER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sequence_number IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchBySequenceNumber(Long... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.SEQUENCE_NUMBER, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByType(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.TYPE, values);
    }

    /**
     * Fetch records that have <code>event_identifier BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfEventIdentifier(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.EVENT_IDENTIFIER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>event_identifier IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByEventIdentifier(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.EVENT_IDENTIFIER, values);
    }

    /**
     * Fetch a unique record that has <code>event_identifier = value</code>
     */
    public com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry fetchOneByEventIdentifier(String value) {
        return fetchOne(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.EVENT_IDENTIFIER, value);
    }

    /**
     * Fetch records that have <code>meta_data BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfMetaData(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.META_DATA, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>meta_data IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByMetaData(byte[]... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.META_DATA, values);
    }

    /**
     * Fetch records that have <code>payload BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfPayload(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByPayload(byte[]... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>payload_revision BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfPayloadRevision(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD_REVISION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload_revision IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByPayloadRevision(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD_REVISION, values);
    }

    /**
     * Fetch records that have <code>payload_type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfPayloadType(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD_TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>payload_type IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByPayloadType(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.PAYLOAD_TYPE, values);
    }

    /**
     * Fetch records that have <code>time_stamp BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchRangeOfTimeStamp(String lowerInclusive, String upperInclusive) {
        return fetchRange(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.TIME_STAMP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>time_stamp IN (values)</code>
     */
    public List<com.hieplp.recipe.user.common.repository.generate.tables.pojos.SnapshotEventEntry> fetchByTimeStamp(String... values) {
        return fetch(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.TIME_STAMP, values);
    }
}
