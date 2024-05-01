package com.venteconcurrentiel2.vente.controller;

import com.venteconcurrentiel2.vente.service.servicemail.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmailTest(){
        emailService.sendEmail("amboarampitiavana8@gmail.com", "test mail ", "This is a test spring boot email");
    return "Email Test send successfully";
    }
}
