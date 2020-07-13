package com.project.crm.backend.repository;

import com.project.crm.backend.model.PasswordResetToken;
import com.project.crm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String passwordResetToken);
    void deleteAllByUser(User user);
}
