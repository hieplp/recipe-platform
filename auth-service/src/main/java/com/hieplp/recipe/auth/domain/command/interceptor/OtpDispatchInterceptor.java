package com.hieplp.recipe.auth.domain.command.interceptor;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.otp.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.ResendRegisterOtpCommand;
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
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final int DEFAULT_HISTORY_ID_LENGTH = 10;

    private final AuthConfig authConfig;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

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
        var doesEmailExist = queryGateway.query(new CheckEmailExistenceQuery(command.getEmail()), boolean.class).join();
        if (doesEmailExist) {
            log.error("Email {} is duplicated", command.getEmail());
            throw new DuplicatedUsernameException(String.format("Email %s is duplicated", command.getEmail()));
        }

        // Check if quota is exceeded
        var quota = queryGateway.query(new GetTodayOtpQuotaQuery(command.getEmail(), OtpType.REGISTER.getType()), int.class).join();
        log.info("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getQuota());
        if (quota >= authConfig.getRegisterOtp().getQuota()) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Confirmation
    // -------------------------------------------------------------------------
    private void handle(ConfirmRegisterOtpCommand command) {
        try {
            log.info("Handle confirm register otp command interceptor: {}", command);

            // Validate OTP
            var otp = validateOtp(command.getOtpId());

            // Check if OTP issue times is exceeded
            var quota = queryGateway.query(new GetTodayOtpHistoryQuotaQuery(otp.getOtpId(), OtpHistoryType.CONFIRM.getType()), int.class).join();
            log.info("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getWrongQuota());
            if (quota >= authConfig.getRegisterOtp().getWrongQuota()) {
                log.error("Quota is exceeded");
                throw new ExceededOtpQuotaException("Quota is exceeded");
            }

            // Check if OTP code is correct
            if (!otp.getOtpCode().equals(command.getOtpCode())) {
                log.error("OTP {} is wrong", command.getOtpId());
                throw new WrongOtpException("OTP is wrong");
            }

            saveRegisterOtpConfirmationHistory(command, OtpHistoryStatus.CORRECT);
        } catch (WrongOtpException e) {
            saveRegisterOtpConfirmationHistory(command, OtpHistoryStatus.WRONG);
            throw e;
        }
    }

    private void saveRegisterOtpConfirmationHistory(ConfirmRegisterOtpCommand command, OtpHistoryStatus status) {
        var createOtpHistoryCommand = CreateOtpHistoryCommand.builder()
                .otpHistoryId(GeneratorUtil.generateId(IdLength.OTP_HISTORY_ID))
                .otpId(command.getOtpId())
                .otpCode(command.getOtpCode())
                .type(OtpHistoryType.CONFIRM.getType())
                .status(status.getStatus())
                .createdBy("anonymous")
                .build();
        commandGateway.sendAndWait(createOtpHistoryCommand);
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Resend
    // -------------------------------------------------------------------------
    private void handle(ResendRegisterOtpCommand command) {
        log.info("Validate resend register otp command: {}", command);
        // Validate OTP
        validateOtp(command.getOtpId());

        // Check if quota is exceeded
        var quota = queryGateway.query(new GetTodayOtpHistoryQuotaQuery(command.getOtpId(), OtpHistoryType.RESEND.getType()), int.class).join();
        log.info("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getWrongQuota());
        if (quota >= authConfig.getRegisterOtp().getResendQuota()) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }
    }

    // -------------------------------------------------------------------------
    // XXX Common methods
    // -------------------------------------------------------------------------
    private OtpEntity validateOtp(String otpId) {
        var otp = queryGateway.query(new GetOtpQuery(otpId), OtpEntity.class).join();

        // Check if OTP exists
        if (otp == null) {
            log.error("OTP {} not found", otpId);
            throw new NotFoundException("OTP not found");
        }

        // Check if OTP is for register
        if (!OtpType.REGISTER.getType().equals(otp.getType())) {
            log.error("OTP {} is not for register", otpId);
            throw new BadRequestException("OTP is not for register");
        }

        // Check OTP issuedAt
        if (otp.getIssuedAt().isAfter(LocalDateTime.now())) {
            log.error("OTP {} is not issued yet", otpId);
            throw new BadRequestException("OTP is not issued yet");
        }

        // Check if OTP is expired
        if (otp.getExpiredAt().isBefore(LocalDateTime.now())) {
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
}
