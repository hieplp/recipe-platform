/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SnapshotEventEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String aggregateIdentifier;
    private Long sequenceNumber;
    private String type;
    private String eventIdentifier;
    private byte[] metaData;
    private byte[] payload;
    private String payloadRevision;
    private String payloadType;
    private String timeStamp;

    public SnapshotEventEntry() {
    }

    public SnapshotEventEntry(SnapshotEventEntry value) {
        this.aggregateIdentifier = value.aggregateIdentifier;
        this.sequenceNumber = value.sequenceNumber;
        this.type = value.type;
        this.eventIdentifier = value.eventIdentifier;
        this.metaData = value.metaData;
        this.payload = value.payload;
        this.payloadRevision = value.payloadRevision;
        this.payloadType = value.payloadType;
        this.timeStamp = value.timeStamp;
    }

    public SnapshotEventEntry(
            String aggregateIdentifier,
            Long sequenceNumber,
            String type,
            String eventIdentifier,
            byte[] metaData,
            byte[] payload,
            String payloadRevision,
            String payloadType,
            String timeStamp
    ) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.sequenceNumber = sequenceNumber;
        this.type = type;
        this.eventIdentifier = eventIdentifier;
        this.metaData = metaData;
        this.payload = payload;
        this.payloadRevision = payloadRevision;
        this.payloadType = payloadType;
        this.timeStamp = timeStamp;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.aggregate_identifier</code>.
     */
    public String getAggregateIdentifier() {
        return this.aggregateIdentifier;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.aggregate_identifier</code>.
     */
    public SnapshotEventEntry setAggregateIdentifier(String aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.sequence_number</code>.
     */
    public Long getSequenceNumber() {
        return this.sequenceNumber;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.sequence_number</code>.
     */
    public SnapshotEventEntry setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.type</code>.
     */
    public SnapshotEventEntry setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.event_identifier</code>.
     */
    public String getEventIdentifier() {
        return this.eventIdentifier;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.event_identifier</code>.
     */
    public SnapshotEventEntry setEventIdentifier(String eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.meta_data</code>.
     */
    public byte[] getMetaData() {
        return this.metaData;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.meta_data</code>.
     */
    public SnapshotEventEntry setMetaData(byte[] metaData) {
        this.metaData = metaData;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.payload</code>.
     */
    public byte[] getPayload() {
        return this.payload;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.payload</code>.
     */
    public SnapshotEventEntry setPayload(byte[] payload) {
        this.payload = payload;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.payload_revision</code>.
     */
    public String getPayloadRevision() {
        return this.payloadRevision;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.payload_revision</code>.
     */
    public SnapshotEventEntry setPayloadRevision(String payloadRevision) {
        this.payloadRevision = payloadRevision;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.payload_type</code>.
     */
    public String getPayloadType() {
        return this.payloadType;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.payload_type</code>.
     */
    public SnapshotEventEntry setPayloadType(String payloadType) {
        this.payloadType = payloadType;
        return this;
    }

    /**
     * Getter for <code>user.snapshot_event_entry.time_stamp</code>.
     */
    public String getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Setter for <code>user.snapshot_event_entry.time_stamp</code>.
     */
    public SnapshotEventEntry setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SnapshotEventEntry (");

        sb.append(aggregateIdentifier);
        sb.append(", ").append(sequenceNumber);
        sb.append(", ").append(type);
        sb.append(", ").append(eventIdentifier);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(payloadRevision);
        sb.append(", ").append(payloadType);
        sb.append(", ").append(timeStamp);

        sb.append(")");
        return sb.toString();
    }
}
