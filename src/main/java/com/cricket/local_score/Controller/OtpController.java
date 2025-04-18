package com.cricket.local_score.Controller;

import com.cricket.local_score.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OtpController {


    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {

        try {
            emailService.sendOtpEmail(email);
            return new ResponseEntity<>("OTP sent successfully to " + email, HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Failed to send OTP: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {

        if (!emailService.isExist(email)) {
            return new ResponseEntity<>("Expired OTP or Does not Exist",HttpStatus.NOT_ACCEPTABLE);

        } else if (emailService.isValidOtp(email,otp)) {
            return new ResponseEntity<>("OTP verified successfully!",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Invalid OTP!",HttpStatus.NOT_ACCEPTABLE);
    }

}
