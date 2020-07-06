package com.project.crm.frontend.forms;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
public class RemedyFormValidatorTest {

    @Autowired
    private Validator validator;

    private RemedyRegisterForm remedyRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setRemedyRegisterForm(){
        remedyRegisterForm = new RemedyRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        remedyRegisterForm = null;
    }


    @Test
    public void validRemedyShouldPassValidation() {
        remedyRegisterForm.setRemedyTypeId((long) 1);
        remedyRegisterForm.setPharmacologicalGroupId((long) 1);
        remedyRegisterForm.setInternationalNameId((long) 1);
        remedyRegisterForm.setName("анальгин");
        remedyRegisterForm.setDosageId((long) 1);
        remedyRegisterForm.setRemediesFormId((long) 1);
        Set<ConstraintViolation<RemedyRegisterForm>> violations = validator.validate(remedyRegisterForm);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validation_null_ExpectFailValidation() {
        remedyRegisterForm.setRemedyTypeId((long) 1);
        remedyRegisterForm.setPharmacologicalGroupId((long) 1);
        remedyRegisterForm.setInternationalNameId((long) 1);
        remedyRegisterForm.setName("анальгин");
        remedyRegisterForm.setDosageId((long) 1);
        remedyRegisterForm.setRemediesFormId(null);

        Set<ConstraintViolation<RemedyRegisterForm>> violations = validator.validate(remedyRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("remediesFormId", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_name_ExpectFailValidation_shouldContainOnlyLetters() {
        remedyRegisterForm.setRemedyTypeId((long) 1);
        remedyRegisterForm.setPharmacologicalGroupId((long) 1);
        remedyRegisterForm.setInternationalNameId((long) 1);
        remedyRegisterForm.setName("something12345");
        remedyRegisterForm.setDosageId((long) 1);
        remedyRegisterForm.setRemediesFormId((long) 1);
        Set<ConstraintViolation<RemedyRegisterForm>> violations = validator.validate(remedyRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(1, violations.size());
        assertFalse(errorMessages.containsAll(Arrays.asList("Название должно содержать только буквы : ${validatedValue}")));
    }


}

