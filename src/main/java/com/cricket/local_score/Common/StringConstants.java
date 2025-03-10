package com.cricket.local_score.Common;

public class StringConstants {
    public static String SUBJECT = "Your One-Time Password (OTP) for LocalScore Access";

    public static String GETBODY(String otp){
        return  "<div style='font-family: Arial, sans-serif; padding: 15px; color: #333;'>" +
                "<h2><b>Dear User,</b></h2>" +
                "<p>We received a request to verify your identity for accessing your <strong>LocalScore</strong> account.</p>" +
                "<p style='font-size: 18px;'><strong>🛡️ Your OTP is: <span style='color: #007bff; font-size: 24px;'>" + otp + "</span></strong></p>" +
                "<p>This OTP is <strong>valid for 10 minutes</strong> and can only be used <strong>once</strong>.</p>" +
                "<p><strong>⚠️ For your security, do not share this OTP with anyone.</strong></p>" +
                "<hr>" +
                "<p>If you did not request this code, please ignore this email or contact our <strong>support team</strong> immediately.</p>" +
                "<p>📧 <strong>Email:</strong> <a href='mailto:localscore.team@gmail.com'>localscore.team@gmail.com</a></p>" +
                "<p>📞 <strong>Support Contact:<br>" +
                "</strong> <a href='tel:8008906106'>+91 80089 06106</a><br>" +
                "<a href='tel:8688241584'>+91 86882 41584</a><br>"+
                "<a href='tel:9701812972'>+91 97018 12972</a><br></p>" +
                "<p>Thank you for choosing <strong>LocalScore</strong>!</p>" +
                "<br><p><em>Best Regards,</em><br><strong>LocalScore Support Team</strong></p>" +
                "</div>";
    }
}
