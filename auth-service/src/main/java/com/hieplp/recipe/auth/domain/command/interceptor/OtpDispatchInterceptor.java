package com.hieplp.recipe.auth.domain.command.interceptor;

import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.register.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
import com.hieplp.recipe.common.query.queries.user.CheckEmailExistenceQuery;
import com.hieplp.recipe.common.query.queries.user.CheckUsernameExistenceQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final AuthConfig authConfig;
    private final QueryGateway queryGateway;

    @NonNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NonNull List<? extends CommandMessage<?>> messages) {
        return (i, m) -> {

            if (CreateRegisterOtpCommand.class.equals(m.getPayloadType())) {
                var command = (CreateRegisterOtpCommand) m.getPayload();
                validate(command);
            }

            return m;
        };
    }

    private void validate(CreateRegisterOtpCommand command) {
        log.debug("Validate create register otp command: {}", command);

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
        log.debug("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getQuota());
        if (quota >= authConfig.getRegisterOtp().getQuota()) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }
    }
}
