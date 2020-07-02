package com.project.crm.frontend.forms;

import com.project.crm.frontend.forms.PositionRegisterForm;
import org.junit.Assert;
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
public class PositionFormValidatorTest {

    @Autowired
    private Validator validator;

    private PositionRegisterForm positionRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpPositionRegisterForm(){
        positionRegisterForm = new PositionRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        positionRegisterForm = null;
    }

    @Test
    public void invalidPositionNameShouldFailValidation() {

        positionRegisterForm.setName("");
        Set<ConstraintViolation<PositionRegisterForm>> violations = validator.validate(positionRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Обязательное поле")));
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validPositionNameShouldPassValidation() {

        positionRegisterForm.setName("something");
        Set<ConstraintViolation<PositionRegisterForm>> violations = validator.validate(positionRegisterForm);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void invalidPositionNameShouldContainOnlyLetters() {

        positionRegisterForm.setName("something2222");
        Set<ConstraintViolation<PositionRegisterForm>> violations = validator.validate(positionRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(1, violations.size());
        assertFalse(errorMessages.containsAll(Arrays.asList("Название должно содержать только буквы : ${validatedValue}")));

    }

}
