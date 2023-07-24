package com.hieplp.recipe.notification.common.model;

import com.hieplp.recipe.notification.common.entity.TemplateEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BuiltTemplateModel extends TemplateEntity {
}
