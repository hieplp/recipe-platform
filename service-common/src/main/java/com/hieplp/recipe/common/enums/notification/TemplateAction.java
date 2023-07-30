package com.hieplp.recipe.common.enums.notification;

public enum TemplateAction {
    REGISTER("register"),
    FORGOT("forgot"),
    ;

    private final String action;

    TemplateAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
