/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate;


import com.hieplp.recipe.user.common.repository.generate.tables.User;
import com.hieplp.recipe.user.common.repository.generate.tables.*;
import com.hieplp.recipe.user.common.repository.generate.tables.records.*;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * user.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AssociationValueEntryRecord> KEY_ASSOCIATION_VALUE_ENTRY_PRIMARY = Internal.createUniqueKey(AssociationValueEntry.ASSOCIATION_VALUE_ENTRY, DSL.name("KEY_association_value_entry_PRIMARY"), new TableField[]{AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.ID}, true);
    public static final UniqueKey<DeadLetterEntryRecord> KEY_DEAD_LETTER_ENTRY_PRIMARY = Internal.createUniqueKey(DeadLetterEntry.DEAD_LETTER_ENTRY, DSL.name("KEY_dead_letter_entry_PRIMARY"), new TableField[]{DeadLetterEntry.DEAD_LETTER_ENTRY.DEAD_LETTER_ID}, true);
    public static final UniqueKey<DeadLetterEntryRecord> KEY_DEAD_LETTER_ENTRY_UKHLR8IO86J74QY298XF720N16V = Internal.createUniqueKey(DeadLetterEntry.DEAD_LETTER_ENTRY, DSL.name("KEY_dead_letter_entry_UKhlr8io86j74qy298xf720n16v"), new TableField[]{DeadLetterEntry.DEAD_LETTER_ENTRY.PROCESSING_GROUP, DeadLetterEntry.DEAD_LETTER_ENTRY.SEQUENCE_IDENTIFIER, DeadLetterEntry.DEAD_LETTER_ENTRY.SEQUENCE_INDEX}, true);
    public static final UniqueKey<DomainEventEntryRecord> KEY_DOMAIN_EVENT_ENTRY_PRIMARY = Internal.createUniqueKey(DomainEventEntry.DOMAIN_EVENT_ENTRY, DSL.name("KEY_domain_event_entry_PRIMARY"), new TableField[]{DomainEventEntry.DOMAIN_EVENT_ENTRY.GLOBAL_INDEX}, true);
    public static final UniqueKey<DomainEventEntryRecord> KEY_DOMAIN_EVENT_ENTRY_UK8S1F994P4LA2IPB13ME2XQM1W = Internal.createUniqueKey(DomainEventEntry.DOMAIN_EVENT_ENTRY, DSL.name("KEY_domain_event_entry_UK8s1f994p4la2ipb13me2xqm1w"), new TableField[]{DomainEventEntry.DOMAIN_EVENT_ENTRY.AGGREGATE_IDENTIFIER, DomainEventEntry.DOMAIN_EVENT_ENTRY.SEQUENCE_NUMBER}, true);
    public static final UniqueKey<DomainEventEntryRecord> KEY_DOMAIN_EVENT_ENTRY_UK_FWE6LSA8BFO6HYAS6UD3M8C7X = Internal.createUniqueKey(DomainEventEntry.DOMAIN_EVENT_ENTRY, DSL.name("KEY_domain_event_entry_UK_fwe6lsa8bfo6hyas6ud3m8c7x"), new TableField[]{DomainEventEntry.DOMAIN_EVENT_ENTRY.EVENT_IDENTIFIER}, true);
    public static final UniqueKey<PasswordRecord> KEY_PASSWORD_PRIMARY = Internal.createUniqueKey(Password.PASSWORD, DSL.name("KEY_password_PRIMARY"), new TableField[]{Password.PASSWORD.USERID}, true);
    public static final UniqueKey<SagaEntryRecord> KEY_SAGA_ENTRY_PRIMARY = Internal.createUniqueKey(SagaEntry.SAGA_ENTRY, DSL.name("KEY_saga_entry_PRIMARY"), new TableField[]{SagaEntry.SAGA_ENTRY.SAGA_ID}, true);
    public static final UniqueKey<SnapshotEventEntryRecord> KEY_SNAPSHOT_EVENT_ENTRY_PRIMARY = Internal.createUniqueKey(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY, DSL.name("KEY_snapshot_event_entry_PRIMARY"), new TableField[]{SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.AGGREGATE_IDENTIFIER, SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.SEQUENCE_NUMBER, SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.TYPE}, true);
    public static final UniqueKey<SnapshotEventEntryRecord> KEY_SNAPSHOT_EVENT_ENTRY_UK_E1UUCJSEO68GOPMND0VGDL44H = Internal.createUniqueKey(SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY, DSL.name("KEY_snapshot_event_entry_UK_e1uucjseo68gopmnd0vgdl44h"), new TableField[]{SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY.EVENT_IDENTIFIER}, true);
    public static final UniqueKey<TokenEntryRecord> KEY_TOKEN_ENTRY_PRIMARY = Internal.createUniqueKey(TokenEntry.TOKEN_ENTRY, DSL.name("KEY_token_entry_PRIMARY"), new TableField[]{TokenEntry.TOKEN_ENTRY.PROCESSOR_NAME, TokenEntry.TOKEN_ENTRY.SEGMENT}, true);
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER_, DSL.name("KEY_user_PRIMARY"), new TableField[]{User.USER_.USERID}, true);
    public static final UniqueKey<UserRecord> KEY_USER_USERNAME = Internal.createUniqueKey(User.USER_, DSL.name("KEY_user_username"), new TableField[]{User.USER_.USERNAME}, true);
}
