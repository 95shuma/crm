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
import java.util.Set;
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
        hospitalRegisterForm.setHouseNum(null);
        hospitalRegisterForm.setPlaceId(null);
        Set<ConstraintViolation<HospitalRegisterForm>> violations = validator.validate(hospitalRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        assertEquals(4, violations.size());
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validHospitalShouldPassValidation() {

        hospitalRegisterForm.setName("something");
        hospitalRegisterForm.setHouseNum("11");
        hospitalRegisterForm.setStreet("something");
        hospitalRegisterForm.setPlaceId((long) 1);
        Set<ConstraintViolation<HospitalRegisterForm>> violations = validator.validate(hospitalRegisterForm);
        assertTrue(violations.isEmpty());
    }
}

