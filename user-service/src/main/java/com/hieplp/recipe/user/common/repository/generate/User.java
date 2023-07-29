/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate;


import com.hieplp.recipe.user.common.repository.generate.tables.*;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class User extends SchemaImpl {

    /**
     * The reference instance of <code>user</code>
     */
    public static final User USER = new User();
    private static final long serialVersionUID = 1L;
    /**
     * The table <code>user.association_value_entry</code>.
     */
    public final AssociationValueEntry ASSOCIATION_VALUE_ENTRY = AssociationValueEntry.ASSOCIATION_VALUE_ENTRY;

    /**
     * The table <code>user.dead_letter_entry</code>.
     */
    public final DeadLetterEntry DEAD_LETTER_ENTRY = DeadLetterEntry.DEAD_LETTER_ENTRY;

    /**
     * The table <code>user.domain_event_entry</code>.
     */
    public final DomainEventEntry DOMAIN_EVENT_ENTRY = DomainEventEntry.DOMAIN_EVENT_ENTRY;

    /**
     * The table <code>user.hibernate_sequence</code>.
     */
    public final HibernateSequence HIBERNATE_SEQUENCE = HibernateSequence.HIBERNATE_SEQUENCE;

    /**
     * The table <code>user.password</code>.
     */
    public final Password PASSWORD = Password.PASSWORD;

    /**
     * The table <code>user.saga_entry</code>.
     */
    public final SagaEntry SAGA_ENTRY = SagaEntry.SAGA_ENTRY;

    /**
     * The table <code>user.snapshot_event_entry</code>.
     */
    public final SnapshotEventEntry SNAPSHOT_EVENT_ENTRY = SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY;

    /**
     * The table <code>user.token_entry</code>.
     */
    public final TokenEntry TOKEN_ENTRY = TokenEntry.TOKEN_ENTRY;

    /**
     * The table <code>user.user</code>.
     */
    public final com.hieplp.recipe.user.common.repository.generate.tables.User USER_ = com.hieplp.recipe.user.common.repository.generate.tables.User.USER_;

    /**
     * No further instances allowed
     */
    private User() {
        super("user", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                AssociationValueEntry.ASSOCIATION_VALUE_ENTRY,
                DeadLetterEntry.DEAD_LETTER_ENTRY,
                DomainEventEntry.DOMAIN_EVENT_ENTRY,
                HibernateSequence.HIBERNATE_SEQUENCE,
                Password.PASSWORD,
                SagaEntry.SAGA_ENTRY,
                SnapshotEventEntry.SNAPSHOT_EVENT_ENTRY,
                TokenEntry.TOKEN_ENTRY,
                com.hieplp.recipe.user.common.repository.generate.tables.User.USER_);
    }
}
