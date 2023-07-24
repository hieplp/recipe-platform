package com.hieplp.recipe.notification.common.util;

import com.hieplp.recipe.notification.common.entity.TemplateEntity;
import com.hieplp.recipe.notification.common.model.BuiltTemplateModel;
import org.springframework.beans.BeanUtils;

import java.util.Map;

public class TemplateUtil {
    private static final String TEMPLATE_DELIMITER = "$";

    public static BuiltTemplateModel buildTemplate(TemplateEntity template, Map<String, String> params) {
        // Clone
        var builtTemplate = new BuiltTemplateModel();
        BeanUtils.copyProperties(template, builtTemplate);

        // Build template
        params.forEach((key, value) -> {
            builtTemplate.setSubject(builtTemplate.getSubject().replace(TEMPLATE_DELIMITER + key + TEMPLATE_DELIMITER, value));
            builtTemplate.setContent(builtTemplate.getContent().replace(TEMPLATE_DELIMITER + key + TEMPLATE_DELIMITER, value));
        });

        return builtTemplate;
    }
}
