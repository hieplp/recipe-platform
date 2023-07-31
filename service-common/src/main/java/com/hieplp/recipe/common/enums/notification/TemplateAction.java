package com.hieplp.recipe.common.enums.notification;

import lombok.Getter;

@Getter
public enum TemplateAction {
    REGISTER("register"),
    FORGOT("forgot"),
    ;

    private final String action;

    TemplateAction(String action) {
        this.action = action;
    }

}
