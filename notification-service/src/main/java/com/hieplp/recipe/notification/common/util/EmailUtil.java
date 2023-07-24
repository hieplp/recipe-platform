package com.hieplp.recipe.notification.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

@Slf4j
public class EmailUtil {
    public static void sendMime(JavaMailSender sender,
                                String sendTo,
                                String subject,
                                String content) throws MessagingException {
        log.info("Send mime email to {} with subject {} and content {}", sendTo, subject, content);
        var message = sender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(content);
        sender.send(message);
    }
}
