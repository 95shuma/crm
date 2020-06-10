package com.project.crm.backend.repository;

import com.project.crm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByInn(String inn);
    Optional<User> findByName(String name);
    boolean existsByInn(String inn);
}
