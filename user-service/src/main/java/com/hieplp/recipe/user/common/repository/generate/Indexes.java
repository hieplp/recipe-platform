/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.user.common.repository.generate;


import com.hieplp.recipe.user.common.repository.generate.tables.AssociationValueEntry;
import com.hieplp.recipe.user.common.repository.generate.tables.DeadLetterEntry;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in user.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index DEAD_LETTER_ENTRY_IDXE67WCX5FIQ9HL4Y4QKHLCJ9CG = Internal.createIndex(DSL.name("IDXe67wcx5fiq9hl4y4qkhlcj9cg"), DeadLetterEntry.DEAD_LETTER_ENTRY, new OrderField[]{DeadLetterEntry.DEAD_LETTER_ENTRY.PROCESSING_GROUP}, false);
    public static final Index ASSOCIATION_VALUE_ENTRY_IDXGV5K1V2MH6FRXUY5C0HGBAU94 = Internal.createIndex(DSL.name("IDXgv5k1v2mh6frxuy5c0hgbau94"), AssociationValueEntry.ASSOCIATION_VALUE_ENTRY, new OrderField[]{AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.SAGA_ID, AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.SAGA_TYPE}, false);
    public static final Index ASSOCIATION_VALUE_ENTRY_IDXK45EQNXKGD8HPDN6XIXN8SGFT = Internal.createIndex(DSL.name("IDXk45eqnxkgd8hpdn6xixn8sgft"), AssociationValueEntry.ASSOCIATION_VALUE_ENTRY, new OrderField[]{AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.SAGA_TYPE, AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.ASSOCIATION_KEY, AssociationValueEntry.ASSOCIATION_VALUE_ENTRY.ASSOCIATION_VALUE}, false);
    public static final Index DEAD_LETTER_ENTRY_IDXRWUCPGS6SN93LDGOEH2Q9K6BN = Internal.createIndex(DSL.name("IDXrwucpgs6sn93ldgoeh2q9k6bn"), DeadLetterEntry.DEAD_LETTER_ENTRY, new OrderField[]{DeadLetterEntry.DEAD_LETTER_ENTRY.PROCESSING_GROUP, DeadLetterEntry.DEAD_LETTER_ENTRY.SEQUENCE_IDENTIFIER}, false);
}