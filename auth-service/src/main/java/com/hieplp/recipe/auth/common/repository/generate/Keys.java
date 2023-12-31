/*
 * This file is generated by jOOQ.
 */
package com.hieplp.recipe.auth.common.repository.generate;


import com.hieplp.recipe.auth.common.repository.generate.tables.Otp;
import com.hieplp.recipe.auth.common.repository.generate.tables.Otphistory;
import com.hieplp.recipe.auth.common.repository.generate.tables.TempUser;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtphistoryRecord;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.TempUserRecord;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * authentication.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<OtpRecord> KEY_OTP_PRIMARY = Internal.createUniqueKey(Otp.OTP, DSL.name("KEY_otp_PRIMARY"), new TableField[]{Otp.OTP.OTPID}, true);
    public static final UniqueKey<OtphistoryRecord> KEY_OTPHISTORY_PRIMARY = Internal.createUniqueKey(Otphistory.OTPHISTORY, DSL.name("KEY_otpHistory_PRIMARY"), new TableField[]{Otphistory.OTPHISTORY.OTPHISTORYID}, true);
    public static final UniqueKey<TempUserRecord> KEY_TEMP_USER_PRIMARY = Internal.createUniqueKey(TempUser.TEMP_USER, DSL.name("KEY_temp_user_PRIMARY"), new TableField[]{TempUser.TEMP_USER.USERID}, true);
}
