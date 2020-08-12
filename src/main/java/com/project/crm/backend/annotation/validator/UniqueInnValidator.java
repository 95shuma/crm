package com.project.crm.backend.annotation.validator;

import com.project.crm.backend.annotation.UniqueInn;
import com.project.crm.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueInnValidator implements ConstraintValidator<UniqueInn, String> {

    @Autowired
    private UserRepo userRepo;

    public void initialize(UniqueInn constraint) {
    }

    public boolean isValid(String inn, ConstraintValidatorContext context) {
        try {
            return !userRepo.existsByInn(Long.parseLong(inn));
        }catch (NumberFormatException ignored){}

        return true;
    }
}