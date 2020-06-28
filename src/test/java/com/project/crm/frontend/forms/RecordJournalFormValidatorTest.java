package com.project.crm.frontend.forms;

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
public class RecordJournalFormValidatorTest {

    @Autowired
    private Validator validator;

    private RecordJournalRegisterForm recordJournalRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUpRecordJournalRegisterForm(){
        recordJournalRegisterForm = new RecordJournalRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        recordJournalRegisterForm = null;
    }

    @Test
    public void invalidRecordJournalFormShouldFailValidation() {


        recordJournalRegisterForm.setReason("");
        recordJournalRegisterForm.setDateTime(LocalDateTime.now().minusDays(1));

        Set<ConstraintViolation<RecordJournalRegisterForm>> violations = validator.validate(recordJournalRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Укажите причину", "Нельзя записаться в прошлое")));
        assertFalse(violations.isEmpty());
    }
    @Test
    public void invalidRecordJournalFormDateShouldFailValidation() {


        recordJournalRegisterForm.setReason("something");

        Set<ConstraintViolation<RecordJournalRegisterForm>> violations = validator.validate(recordJournalRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validPositionNameShouldPassValidation() {

        recordJournalRegisterForm.setReason("something");
        recordJournalRegisterForm.setDateTime(LocalDateTime.now().plusDays(1));
        Set<ConstraintViolation<RecordJournalRegisterForm>> violations = validator.validate(recordJournalRegisterForm);
        assertTrue(violations.isEmpty());
    }
}
