package com.hieplp.recipe.auth.domain.command.commands.user.create;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateTempUserCommand {
    @TargetAggregateIdentifier
    @NonNull
    private String userId;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String fullName;
    @NonNull
    private byte[] password;
    @NonNull
    private byte[] salt;
    @NonNull
    private String createdBy;
    @NonNull
    private String referenceId;
}
