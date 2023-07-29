package com.hieplp.recipe.auth.domain.command.interceptor;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.otp.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.query.queries.history.GetTodayOtpHistoryQuotaQuery;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
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

    private final AuthConfig authConfig;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @NonNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NonNull List<? extends CommandMessage<?>> messages) {
        return (i, m) -> {

            if (CreateRegisterOtpCommand.class.equals(m.getPayloadType())) {
                var command = (CreateRegisterOtpCommand) m.getPayload();
                validate(command);
            } else if (ConfirmRegisterOtpCommand.class.equals(m.getPayloadType())) {
                var command = (ConfirmRegisterOtpCommand) m.getPayload();
                try {
                    validate(command);
                    saveRegisterOtpConfirmationHistory(command, OtpHistoryStatus.CORRECT);
                } catch (WrongOtpException e) {
                    saveRegisterOtpConfirmationHistory(command, OtpHistoryStatus.WRONG);
                    throw e;
                }
            }

            return m;
        };
    }

    // -------------------------------------------------------------------------
    // XXX Register OTP Creation
    // -------------------------------------------------------------------------

    private void validate(CreateRegisterOtpCommand command) {
        log.info("Validate create register otp command: {}", command);

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
    private void validate(ConfirmRegisterOtpCommand command) {
        log.info("Validate confirm register otp command: {}", command);

        var otp = queryGateway.query(new GetOtpQuery(command.getOtpId()), OtpEntity.class).join();

        // Check if OTP exists
        if (otp == null) {
            log.error("OTP {} not found", command.getOtpId());
            throw new NotFoundException("OTP not found");
        }

        // Check if OTP is for register
        if (!OtpType.REGISTER.getType().equals(otp.getType())) {
            log.error("OTP {} is not for register", command.getOtpId());
            throw new BadRequestException("OTP is not for register");
        }

        // Check OTP issuedAt
        if (otp.getIssuedAt().isAfter(LocalDateTime.now())) {
            log.error("OTP {} is not issued yet", command.getOtpId());
            throw new BadRequestException("OTP is not issued yet");
        }

        // Check if OTP is expired
        if (otp.getExpiredAt().isBefore(LocalDateTime.now())) {
            log.error("OTP {} is expired", command.getOtpId());
            throw new ExpiredOtpException("OTP is expired");
        }

        // Check if OTP is issued
        if (!OtpStatus.ACTIVATED.getStatus().equals(otp.getStatus())) {
            log.error("OTP {} is issued", command.getOtpId());
            throw new BadRequestException("OTP is issued");
        }

        // Check if OTP issue times is exceeded
        var quota = queryGateway.query(new GetTodayOtpHistoryQuotaQuery(otp.getOtpId(), OtpType.REGISTER.getType()), int.class).join();
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
    }

    private void saveRegisterOtpConfirmationHistory(ConfirmRegisterOtpCommand command, OtpHistoryStatus status) {
        var createOtpHistoryCommand = CreateOtpHistoryCommand.builder()
                .otpId(command.getOtpId())
                .otpCode(command.getOtpCode())
                .type(OtpType.REGISTER.getType())
                .status(status.getStatus())
                .createdBy("anonymous")
                .build();
        commandGateway.sendAndWait(createOtpHistoryCommand);
    }

    // -------------------------------------------------------------------------
    // XXX
    // -------------------------------------------------------------------------
}
