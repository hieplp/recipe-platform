package com.hieplp.recipe.notification.common.entity;

import lombok.Data;

@Data
public class TemplateEntity {
    private String action;
    private Byte sendVia;
    private String subject;
    private String content;
}
