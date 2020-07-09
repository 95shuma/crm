package com.project.crm.frontend.forms;

import com.project.crm.frontend.forms.medicalHistoryForms.DiagnoseResultRegisterForm;
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
public class DiagnoseResultFormValidatorTest {

    @Autowired
    private Validator validator;

    private DiagnoseResultRegisterForm diagnoseResultRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setUpRecordJournalRegisterForm(){
        diagnoseResultRegisterForm = new DiagnoseResultRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        diagnoseResultRegisterForm = null;
    }

    @Test
    public void validDiagnoseResultShouldFailValidation() {


        diagnoseResultRegisterForm.setState(String.valueOf(true));

        Set<ConstraintViolation<DiagnoseResultRegisterForm>> violations = validator.validate(diagnoseResultRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Это поле не может быть пустым")));
        assertFalse(violations.isEmpty());
    }
    @Test
    public void invalidDiagnoseResultShouldPassValidation() {


        diagnoseResultRegisterForm.setState("");

        Set<ConstraintViolation<DiagnoseResultRegisterForm>> violations = validator.validate(diagnoseResultRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(3, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Это поле не может быть пустым", "Выберите один из вариантов")));
        assertFalse(violations.isEmpty());
    }

}
