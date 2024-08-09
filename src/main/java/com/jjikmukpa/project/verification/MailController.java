package com.jjikmukpa.project.verification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

@Slf4j
// ref: https://velog.io/@kimtaehyeun/IT-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%87%BC%ED%95%91%EB%AA%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-Spring-Boot-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%87%BC%ED%95%91%EB%AA%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-SpringBoot-Java-Mail-Sender%EC%9D%B4%EB%A9%94%EC%9D%BC-%EB%B3%B8%EC%9D%B8-%EC%9D%B8%EC%A6%9D
public class MailController {
    private final MailManager mailManager;

    public MailController(MailManager mailManager) {
        this.mailManager = mailManager;
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public String sendMail(@RequestBody Map<String, String> requestBody) throws Exception {
        String email = requestBody.get("email");

        UUID uuid = UUID.randomUUID(); // Generate a random UUID
        String key = uuid.toString().substring(0, 7); // Use only the first 7 characters for the key
        String subject = "인증번호 입력을 위한 메일 전송";
        String content = "인증 번호 : " + key;

        log.info("🍔🍔🍔🍔🍔🍔 Sending email to: " + email);

        mailManager.send(email, subject, content);
        key = SHA256Util.getEncrypt(key, email);
        return key;
    }

    @PostMapping("/checkMail") //
    @ResponseBody
    public boolean CheckMail(String key, String insertKey,String email) throws Exception {
        insertKey = SHA256Util.getEncrypt(insertKey, email);

        if(key.equals(insertKey)) {
            return true;
        }
        return false;
    }

    // DTO classes for request bodies
//    public static class EmailRequestDto {
//        private String email;
//        // Getters and setters
//    }
//
//    public static class VerificationRequestDto {
//        private String key;
//        private String insertKey;
//        private String email;
//        // Getters and setters
//    }
}

