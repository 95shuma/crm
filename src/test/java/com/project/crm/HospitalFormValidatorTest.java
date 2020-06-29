package com.project.crm;

import com.project.crm.frontend.forms.HospitalRegisterForm;
import com.project.crm.frontend.forms.PositionRegisterForm;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
public class HospitalFormValidatorTest {

    @Autowired
    private Validator validator;

    private HospitalRegisterForm hospitalRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpHospitalRegisterForm(){
        hospitalRegisterForm = new HospitalRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        hospitalRegisterForm = null;
    }

    @Test
    public void invalidHospitalShouldFailValidation() {

        hospitalRegisterForm.setName("");
        hospitalRegisterForm.setStreet("");
        hospitalRegisterForm.setHouseNum("");
        hospitalRegisterForm.setPlaceId(null);
        Set<ConstraintViolation<HospitalRegisterForm>> violations = validator.validate(hospitalRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        assertEquals(4, violations.size());
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validHospitalShouldPassValidation() {

        hospitalRegisterForm.setName("something");
        hospitalRegisterForm.setHouseNum("something");
        hospitalRegisterForm.setStreet("something");
        hospitalRegisterForm.setPlaceId((long) 1);
        Set<ConstraintViolation<HospitalRegisterForm>> violations = validator.validate(hospitalRegisterForm);
        assertTrue(violations.isEmpty());
    }
}

