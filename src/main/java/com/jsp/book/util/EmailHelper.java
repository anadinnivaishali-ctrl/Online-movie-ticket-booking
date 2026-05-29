package com.jsp.book.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailHelper {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    private static final String FROM_EMAIL = "no-reply@bookmy-ticket.com";
    private static final String FROM_NAME = "Book-My-Ticket";
    private static final String SUBJECT = "OTP for Creating Account";

    @Async
    public void sendOtp(int otp, String name, String email) {

        System.out.println("🔥 EMAIL METHOD CALLED");
        System.out.println("🔥 OTP IN EMAIL: " + otp);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

            helper.setFrom(FROM_EMAIL, FROM_NAME);
            helper.setTo(email);
            helper.setSubject(SUBJECT);

            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("otp", otp);

            String body = templateEngine.process("email-template", context);
            helper.setText(body, true);

            mailSender.send(message);

            System.out.println("🔥 EMAIL SENT SUCCESSFULLY");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}