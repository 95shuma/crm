package com.project.crm.backend.annotation.validator;

import com.project.crm.backend.annotation.UniqueCodePlace;
import com.project.crm.backend.repository.PlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCodePlaceValidator implements ConstraintValidator<UniqueCodePlace, String> {

    @Autowired
    private PlaceRepo placeRepo;

    public void initialize(UniqueCodePlace constraint) {
    }

    public boolean isValid(String codePlace, ConstraintValidatorContext context) {
        try {
            return !placeRepo.existsByCodePlace(Long.parseLong(codePlace));
        }catch (NumberFormatException ignored){}

        return true;
    }
}