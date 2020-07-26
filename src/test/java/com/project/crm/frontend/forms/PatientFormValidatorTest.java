package com.project.crm.frontend.forms;

import com.project.crm.frontend.forms.HospitalRegisterForm;
import com.project.crm.frontend.forms.PatientRegisterForm;
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
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
public class PatientFormValidatorTest {

    @Autowired
    private Validator validator;

    private PatientRegisterForm patientRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpPatientRegisterForm(){
        patientRegisterForm = new PatientRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        patientRegisterForm = null;
    }

    @Test
    public void invalidPatientForAllWithoutDateShouldFailValidation() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        patientRegisterForm.setBirthDate(today);

        Set<ConstraintViolation<PatientRegisterForm>> violations = validator.validate(patientRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(17, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Обязательное поле", "Требуется ввести 14 цифр", "Пароль должен содержать минимум 8 символов")));
        assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidPatientForDateShouldFailValidation() {

        patientRegisterForm.setInn("12345678910123");
        patientRegisterForm.setPassword("something");
        patientRegisterForm.setDocumentNumber("AN1234567");
        patientRegisterForm.setName("something");
        patientRegisterForm.setSurname("something");
        patientRegisterForm.setMiddleName("something");
        patientRegisterForm.setBirthDate(null);
        patientRegisterForm.setGender("something");
        patientRegisterForm.setPlaceId((long) 1);
        patientRegisterForm.setHospitalId((long) 1);
        patientRegisterForm.setRoleId((long) 1);
        Set<ConstraintViolation<PatientRegisterForm>> violations = validator.validate(patientRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());

    }


    @Test
    public void validPatientShouldPassValidation() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        patientRegisterForm.setInn("12345678910123");
        patientRegisterForm.setPassword("something");
        patientRegisterForm.setDocumentNumber("AN12345");
        patientRegisterForm.setName("something");
        patientRegisterForm.setSurname("something");
        patientRegisterForm.setMiddleName("something");
        patientRegisterForm.setBirthDate(today);
        patientRegisterForm.setGender("something");
        patientRegisterForm.setPlaceId((long) 1);
        patientRegisterForm.setHospitalId((long) 1);
        patientRegisterForm.setRoleId((long) 1);
        Set<ConstraintViolation<PatientRegisterForm>> violations = validator.validate(patientRegisterForm);
        assertFalse(violations.isEmpty());
    }


}

