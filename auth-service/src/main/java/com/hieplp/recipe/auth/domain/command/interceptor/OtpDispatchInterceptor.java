package com.hieplp.recipe.auth.domain.command.interceptor;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.auth.domain.query.queries.history.GetTodayOtpHistoryQuotaQuery;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
import com.hieplp.recipe.common.enums.otp.OtpHistoryType;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.exception.BadRequestException;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.auth.ExpiredOtpException;
import com.hieplp.recipe.common.exception.auth.WrongOtpException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.query.queries.user.CheckEmailExistenceQuery;
import com.hieplp.recipe.common.query.queries.user.CheckUsernameExistenceQuery;
import com.hieplp.recipe.common.query.queries.user.GetUserIdByEmailQuery;
import com.hieplp.recipe.common.util.DateUtil;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MetaData;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final String USER_ID = "userId";

    private final AuthConfig authConfig;
    private final QueryGateway queryGateway;
    private final OtpHelper otpHelper;

    @NonNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NonNull List<? extends CommandMessage<?>> messages) {
        return (i, m) -> {

            var payloadType = m.getPayloadType();
            var payload = m.getPayload();

            if (CreateRegisterOtpCommand.class.equals(payloadType)) {
                var command = (CreateRegisterOtpCommand) payload;
                handle(command);
            } else if (ConfirmRegisterOtpCommand.class.equals(payloadType)) {
                var command = (ConfirmRegisterOtpCommand) payload;
                handle(command);
            } else if (ResendRegisterOtpCommand.class.equals(payloadType)) {
                var command = (ResendRegisterOtpCommand) payload;
                handle(command);
            } else if (CreateForgotOtpCommand.class.equals(payloadType)) {
                var command = (CreateForgotOtpCommand) payload;
                handle(command);
                // Update metadata
                var metaData = MetaData.with(USER_ID, command.getUserId());
                m.andMetaData(metaData);
            } else if (ConfirmForgotOtpCommand.class.equals(payloadType)) {
                var command = (ConfirmForgotOtpCommand) payload;
                handle(command);
                // Update metadata
                var metaData = MetaData.with(USER_ID, command.getUserId());
                m.andMetaData(metaData);
            } else if (ResendForgotOtpCommand.class.equals(payloadType)) {
                var command = (ResendForgotOtpCommand) payload;
                handle(command);
            }

            return m;
        };
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Creation
    // -------------------------------------------------------------------------

    private void handle(CreateRegisterOtpCommand command) {
        log.info("Handle create register otp command interceptor: {}", command);

        // Check if username exists
        var doesUsernameExist = queryGateway.query(new CheckUsernameExistenceQuery(command.getUsername()), boolean.class).join();
        if (doesUsernameExist) {
            log.error("Username {} is duplicated", command.getUsername());
            throw new DuplicatedUsernameException(String.format("Username %s is duplicated", command.getUsername()));
        }

        // Check if email exists
        var doesEmailExist = queryGateway.query(new CheckEmailExistenceQuery(command.getSendTo()), boolean.class).join();
        if (doesEmailExist) {
            log.error("Email {} is duplicated", command.getSendTo());
            throw new DuplicatedUsernameException(String.format("Email %s is duplicated", command.getSendTo()));
        }

        // Check if quota is exceeded
        validateQuota(command.getSendTo(), authConfig.getRegisterOtp().getQuota(), OtpType.REGISTER);
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Confirmation
    // -------------------------------------------------------------------------
    private void handle(ConfirmRegisterOtpCommand command) {
        try {
            log.info("Handle confirm register otp command interceptor: {}", command);

            // Validate OTP
            var otp = validateOtp(command.getOtpId(), OtpType.REGISTER);

            // Check if OTP issue times is exceeded
            validateHistoryQuota(otp.getOtpId(), authConfig.getRegisterOtp().getWrongQuota(), OtpHistoryType.CONFIRM);

            // Check if OTP code is correct
            validateOtpCode(command.getOtpCode(), otp.getOtpCode());

            // Save wrong/ correct OTP confirmation history to prevent brute force attack
            saveOtpConfirmationHistory(command, OtpHistoryStatus.CORRECT);
        } catch (WrongOtpException e) {
            saveOtpConfirmationHistory(command, OtpHistoryStatus.WRONG);
            throw e;
        }
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Resend
    // -------------------------------------------------------------------------
    private void handle(ResendRegisterOtpCommand command) {
        log.info("Validate resend register otp command: {}", command);

        // Validate OTP
        validateOtp(command.getOtpId(), OtpType.REGISTER);

        // Validate total resend times
        validateHistoryQuota(command.getOtpId(), authConfig.getRegisterOtp().getResendQuota(), OtpHistoryType.RESEND);
    }

    // -------------------------------------------------------------------------
    // XXX Forgot OTP Creation
    // -------------------------------------------------------------------------
    private void handle(CreateForgotOtpCommand command) {
        log.info("Handle create forgot otp command interceptor: {}", command);

        // Check if email exists
        var userId = queryGateway.query(new GetUserIdByEmailQuery(command.getSendTo()), String.class).join();
        if (userId == null) {
            log.error("Email {} is not found", command.getSendTo());
            throw new NotFoundException(String.format("Email %s is not found", command.getSendTo()));
        }
        command.setUserId(userId);

        // Check if quota is exceeded
        validateQuota(command.getSendTo(), authConfig.getForgotOtp().getQuota(), OtpType.FORGOT_PASSWORD);
    }

    // -------------------------------------------------------------------------
    // XXX Forgot OTP Confirmation
    // -------------------------------------------------------------------------
    private void handle(ConfirmForgotOtpCommand command) {
        try {
            log.info("Handle confirm forgot otp command interceptor: {}", command);

            // Validate OTP
            var otp = validateOtp(command.getOtpId(), OtpType.FORGOT_PASSWORD);

            // Update userId in command for further use
            command.setUserId(otp.getCreatedBy());

            // Validate OTP issue times
            validateHistoryQuota(otp.getOtpId(), authConfig.getForgotOtp().getWrongQuota(), OtpHistoryType.CONFIRM);

            // Check if OTP code is correct
            validateOtpCode(command.getOtpCode(), otp.getOtpCode());

            // Save wrong/ correct OTP confirmation history to prevent brute force attack
            saveOtpConfirmationHistory(command, OtpHistoryStatus.CORRECT);
        } catch (WrongOtpException e) {
            saveOtpConfirmationHistory(command, OtpHistoryStatus.CORRECT);
            throw e;
        }
    }

    // -------------------------------------------------------------------------
    // XXX Forgot OTP Resend
    // -------------------------------------------------------------------------
    private void handle(ResendForgotOtpCommand command) {
        log.info("Handle resend forgot otp command interceptor: {}", command);

        // Validate OTP
        var otp = validateOtp(command.getOtpId(), OtpType.FORGOT_PASSWORD);

        // Validate total resend times
        validateHistoryQuota(otp.getOtpId(), authConfig.getForgotOtp().getResendQuota(), OtpHistoryType.RESEND);
    }

    /**
     * Validate OTP, such as existence, type, expiration, etc.
     *
     * @param otpId   id of OTP
     * @param otpType type of OTP {@link OtpType}
     * @return {@link OtpEntity}
     * @throws NotFoundException   if OTP is not found
     * @throws ExpiredOtpException if OTP is expired
     * @throws BadRequestException if OTP is not for register.
     *                             if OTP is not for forgot password
     *                             if OTP is not issued yet
     *                             if OTP is used
     */
    // -------------------------------------------------------------------------
    // XXX Common methods
    // -------------------------------------------------------------------------
    private OtpEntity validateOtp(String otpId, OtpType otpType) {
        var otp = queryGateway.query(new GetOtpQuery(otpId), OtpEntity.class).join();

        // Check if OTP exists
        if (otp == null) {
            log.error("OTP {} not found", otpId);
            throw new NotFoundException("OTP not found");
        }

        // Check if OTP is for register
        if (!otpType.getType().equals(otp.getType())) {
            log.error("OTP {} is not for {}", otpId, otpType.getType());
            throw new BadRequestException(String.format("OTP is not for %s", otpType.getType()));
        }

        // Check OTP issuedAt
        if (otp.getIssuedAt().isAfter(DateUtil.now())) {
            log.error("OTP {} is not issued yet", otpId);
            throw new BadRequestException("OTP is not issued yet");
        }

        // Check if OTP is expired
        if (otp.getExpiredAt().isBefore(DateUtil.now())) {
            log.error("OTP {} is expired", otpId);
            throw new ExpiredOtpException("OTP is expired");
        }

        // Check if OTP is issued
        if (!OtpStatus.ACTIVATED.getStatus().equals(otp.getStatus())) {
            log.error("OTP {} is issued", otpId);
            throw new BadRequestException("OTP is issued");
        }

        return otp;
    }

    /**
     * Validate quota for OTP. like quota for register, forgot password, etc.
     *
     * @param sendTo      email or phone number
     * @param configQuota quota from config
     * @param otpType     otp type {@link OtpType}
     * @throws ExceededOtpQuotaException if quota is exceeded
     */
    private void validateQuota(String sendTo,
                               int configQuota,
                               OtpType otpType) {
        var quota = queryGateway.query(new GetTodayOtpQuotaQuery(sendTo, otpType.getType()), int.class).join();
        log.info("Current quota: {} and config quota: {}", quota, configQuota);
        if (quota >= configQuota) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }
    }

    /**
     * Validate quota for OTP history. like wrong quota, resend quota, etc.
     *
     * @param otpId       otpId to validate
     * @param configQuota quota from config
     * @param historyType history type  {@link OtpHistoryType}
     * @throws ExceededOtpQuotaException if quota is exceeded
     */
    private void validateHistoryQuota(String otpId,
                                      int configQuota,
                                      OtpHistoryType historyType) {
        var quota = queryGateway.query(new GetTodayOtpHistoryQuotaQuery(otpId, historyType.getType()), int.class).join();
        log.info("Current quota: {} and config quota: {}", quota, configQuota);
        if (quota >= configQuota) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }
    }

    /**
     * Validate if otpCode from request is match with otpCode in database.
     *
     * @param otpCodeFromRequest otpCode from request
     * @param otpCodeInDb        otpCode in database
     * @throws WrongOtpException if otpCode from request is not match with otpCode in database
     */
    private void validateOtpCode(String otpCodeFromRequest, String otpCodeInDb) {
        if (!otpCodeInDb.equals(otpCodeFromRequest)) {
            log.error("OTP code is wrong");
            throw new WrongOtpException("OTP code is wrong");
        }
    }

    /**
     * Save otp confirmation history
     *
     * @param command command extends {@link ConfirmOtpCommand}
     * @param status  {@link OtpHistoryStatus}
     */
    private void saveOtpConfirmationHistory(ConfirmOtpCommand command, OtpHistoryStatus status) {
        otpHelper.sendCreateOtpHistoryCommand(CreateOtpHistoryCommand.builder()
                .otpHistoryId(GeneratorUtil.generateId(IdLength.OTP_HISTORY_ID))
                .otpId(command.getOtpId())
                .otpCode(command.getOtpCode())
                .type(OtpHistoryType.CONFIRM.getType())
                .status(status.getStatus())
                .createdBy("anonymous")
                .build());
    }
}
