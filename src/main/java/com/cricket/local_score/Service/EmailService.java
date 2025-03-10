package com.cricket.local_score.Service;

import com.cricket.local_score.Common.OTPData;
import com.cricket.local_score.Common.StringConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, OTPData> otpStorage = new HashMap<>();

    // Generate a 6-digit OTP
    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    // Send OTP via email
    public void sendOtpEmail(String toEmail) throws MessagingException {

        String otp = generateOtp();
        OTPData otpData=new OTPData(otp, Instant.now());
        otpStorage.put(toEmail, otpData);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject(StringConstants.SUBJECT);
        helper.setText(StringConstants.GETBODY(otp), true);

        mailSender.send(message);
    }

    public boolean isExist(String email){
       return otpStorage.containsKey(email);
    }

    public boolean isValidOtp(String email,String otp){
        return otpStorage.get(email).otp().equals(otp);
    }

    @Scheduled(fixedRate = 60*1000)  // Run every 60 seconds (1 minute)
    public void removeExpiredOtps() {
        Iterator<Map.Entry<String, OTPData>> iterator = otpStorage.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, OTPData> entry = iterator.next();
            OTPData otpData = entry.getValue();
            if (otpData.isExpired()) {
                iterator.remove();
            }
        }
    }
}
