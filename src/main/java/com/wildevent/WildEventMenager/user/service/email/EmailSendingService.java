package com.wildevent.WildEventMenager.user.service.email;

import org.springframework.stereotype.Service;

@Service
public class EmailSendingService {

    private final EmailService emailService;

    public EmailSendingService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendWelcomeEmail(String email, String generatedPassword) {
        EmailDetails emailDetails = new EmailDetails(
                email,
                "Your password: " + generatedPassword,
                "Welcome to Wild Event Management!"
        );
        emailService.sendMail(emailDetails);
    }
}
