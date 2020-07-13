package com.project.crm.backend.services;

import com.project.crm.backend.model.PasswordResetToken;
import com.project.crm.backend.model.User;
import com.project.crm.backend.repository.PasswordResetTokenRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.frontend.forms.NewPasswordRegisterForm;
import com.project.crm.frontend.forms.UserRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetTokenRepo passwordResetTokenRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    public String createPasswordResetToken(Long inn){

        passwordResetTokenRepo.deleteAllByUser(userRepo.findByInn(inn).get());

        var pToken = PasswordResetToken.builder()
                .user(userRepo.findByInn(inn).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(1))
                .build();
        passwordResetTokenRepo.save(pToken);
        return pToken.getToken();
    }

    public void resetPassword(NewPasswordRegisterForm newPasswordRegisterForm){
        PasswordResetToken passwordResetToken = passwordResetTokenRepo.findByToken(newPasswordRegisterForm.getToken()).get();
        User user = userRepo.findById(passwordResetToken.getUser().getId()).get();
        user.setPassword(encoder.encode(newPasswordRegisterForm.getFirstPassword()));
        userRepo.save(user);
    }
}
