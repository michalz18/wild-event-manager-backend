package com.wildevent.WildEventMenager.user.service.email;

import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {

    private final EmailService emailService;

    public EmailSendingService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendPasswordResetEmail(String email, String token) {
        String resetUrl = "http://localhost:3000/reset-password/" + token;

        EmailDetails emailDetails = new EmailDetails(
                email,
                "Click the following link to set your password: " + resetUrl,
                "Welcome to Wild Event Manager!"
        );
        emailService.sendMail(emailDetails);
    }
}
