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
public class PlaceFormValidatorTest {

    @Autowired
    private Validator validator;

    private PlaceRegisterForm placeRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setPlaceRegisterForm(){
        placeRegisterForm = new PlaceRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        placeRegisterForm = null;
    }


    @Test
    public void validPlaceShouldPassValidation() {

        placeRegisterForm.setName("something");
        placeRegisterForm.setCodePlace("12345678912345");
        placeRegisterForm.setGroupCode((long) 1);
        Set<ConstraintViolation<PlaceRegisterForm>> violations = validator.validate(placeRegisterForm);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validation_nullCodePlace_ExpectFailValidation() {
        placeRegisterForm.setName("something");
        placeRegisterForm.setCodePlace(null);
        placeRegisterForm.setGroupCode((long) 1);

        Set<ConstraintViolation<PlaceRegisterForm>> violations = validator.validate(placeRegisterForm);
        violations.forEach(violation -> assertEquals("Это поле не может быть пустым", violation.getMessage()));
        violations.forEach(violation -> assertEquals("codePlace", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_name_ExpectFailValidation_shouldContainOnlyLetters() {
        placeRegisterForm.setName("something12345");
        placeRegisterForm.setCodePlace("12345678912345");
        placeRegisterForm.setGroupCode((long) 1);
        Set<ConstraintViolation<PlaceRegisterForm>> violations = validator.validate(placeRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(1, violations.size());
        assertFalse(errorMessages.containsAll(Arrays.asList("Название должно содержать только буквы : ${validatedValue}")));
    }

    @Test
    public void validation_CodePlace_ExpectFailValidation() {
        placeRegisterForm.setName("something");
        placeRegisterForm.setCodePlace("123456789");
        placeRegisterForm.setGroupCode((long) 1);

        Set<ConstraintViolation<PlaceRegisterForm>> violations = validator.validate(placeRegisterForm);
        violations.forEach(violation -> assertEquals("Требуется ввести код из 14 цифр", violation.getMessage()));
        violations.forEach(violation -> assertEquals("codePlace", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
}

