package com.program.email.service;

import com.program.email.entity.EmailValidate;
import com.program.email.entity.User;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;

public interface EmailValidateService {

    void sendPasswordResetEmail(SimpleMailMessage email);

    int insertNewResetRecord(User user, String token, String type);

    List<EmailValidate> findUserByResetToken(String resetToken);

    boolean validateLimitation(String email, long requestPerDay, long interval, String token);

    boolean sendValidateLimitation(String email, long requestPerDay, long interval);
}