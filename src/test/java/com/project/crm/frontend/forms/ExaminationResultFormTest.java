package com.project.crm.frontend.forms;


import com.project.crm.frontend.forms.remediesForm.ExaminationResultForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

@SpringBootTest
public class ExaminationResultFormTest {

    @Autowired
    private Validator validator;

    private ExaminationResultForm examinationResultForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpExaminationResultForm(){
        examinationResultForm = new ExaminationResultForm();
    }

    @AfterEach
    public void tearDown(){
        examinationResultForm = null;
    }

    @Test
    public void invalidExaminationResultShouldFailValidation() {

        examinationResultForm.setLabExaminationResult("");
        examinationResultForm.setInstrumExaminationResult("");
        examinationResultForm.setMedicalHistoryId(null);
        examinationResultForm.setLabExaminationId(null);
        examinationResultForm.setInstrumExaminationId(null);
        examinationResultForm.setGeneralState("");
        Set<ConstraintViolation<ExaminationResultForm>> violations = validator.validate(examinationResultForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        List<String> properties = violations.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString).collect(toList());
        assertEquals(6, violations.size());
        assertTrue(properties.containsAll(Arrays.asList("labExaminationId", "medicalHistoryId", "labExaminationResult",
                "instrumExaminationResult", "instrumExaminationId", "generalState")));
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validExaminationResultShouldPassValidation() {

        examinationResultForm.setLabExaminationResult("something");
        examinationResultForm.setInstrumExaminationResult("something");
        examinationResultForm.setMedicalHistoryId((long)1);
        examinationResultForm.setLabExaminationId((long)1);
        examinationResultForm.setInstrumExaminationId((long)1);
        examinationResultForm.setGeneralState("something");
        Set<ConstraintViolation<ExaminationResultForm>> violations = validator.validate(examinationResultForm);
        assertTrue(violations.isEmpty());
    }
}

