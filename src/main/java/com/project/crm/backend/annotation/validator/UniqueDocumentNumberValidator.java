package com.project.crm.backend.annotation.validator;

import com.project.crm.backend.annotation.UniqueDocumentNumber;
import com.project.crm.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDocumentNumberValidator implements ConstraintValidator<UniqueDocumentNumber, String> {

    @Autowired
    private UserRepo userRepo;

    public void initialize(UniqueDocumentNumber constraint) {
    }

    public boolean isValid(String documentNumber, ConstraintValidatorContext context) {
        return !userRepo.existsByDocumentNumber(documentNumber);
    }
}
