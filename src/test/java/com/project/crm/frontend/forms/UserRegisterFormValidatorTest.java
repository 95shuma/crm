package com.project.crm.frontend.forms;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import static org.junit.Assert.*;

@SpringBootTest
public class UserRegisterFormValidatorTest {

    @Autowired
    private Validator validator;

    private UserRegisterForm userRegisterForm;
    private Calendar calendar;
    java.util.Date today;

    private String correctInn;
    private String innMore14;
    private String innLess14;
    private String correctPassword;
    private String passwordLess8;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpUserRegisterForm(){
        userRegisterForm = new UserRegisterForm();
    }
    @Before
    public void setUpParameters(){
        correctInn = "12345678912345";
        innMore14 = "111111111111111111111111";
        innLess14 = "1111";
        correctPassword = "12345678";
        passwordLess8 = "1111";
        correctDocumentNumber = "ID1234567";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();

    }
    @AfterEach
    public void tearDown(){
        userRegisterForm = null;
    }
    @Test
    public void validation_validUserRegisterForm_ExpectValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);
        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        assertTrue(violations.isEmpty());
    }
    @Test
    public void validation_nullInn_ExpectInnFailValidation() {
        userRegisterForm.setInn(null);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Это поле не может быть пустым", violation.getMessage()));
        violations.forEach(violation -> assertEquals("inn", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_wrongSizeInnMore14_ExpectInnFailValidation() {
        userRegisterForm.setInn(innMore14);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Требуется ввести 14 цифр", violation.getMessage()));
        violations.forEach(violation -> assertEquals("inn", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_wrongSizeInnLess14_ExpectInnFailValidation() {
        userRegisterForm.setInn(innLess14);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Требуется ввести 14 цифр", violation.getMessage()));
        violations.forEach(violation -> assertEquals("inn", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullPassword_ExpectPasswordFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(null);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("password", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_wrongSizePasswordLessThenCorrect_ExpectPasswordFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(passwordLess8);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Пароль должен содержать минимум 8 символов", violation.getMessage()));
        violations.forEach(violation -> assertEquals("password", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullDocumentNumber_ExpectDocumentNumberFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(null);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("documentNumber", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullName_ExpectNameFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(null);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("name", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullSurname_ExpectSurnameFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(null);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("surname", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullBirthDate_ExpectBirthDateFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(null);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("birthDate", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_futureBirthDate_ExpectBirthDateFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(Date.from(Instant.now().plus(10, ChronoUnit.DAYS)));
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Дата рождения не может быть в будущем времени", violation.getMessage()));
        violations.forEach(violation -> assertEquals("birthDate", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullGender_ExpectGenderFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(null);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("gender", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullPlaceId_ExpectPlaceIdFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId(null);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);
        userRegisterForm.setPositionId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("placeId", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullPositionId_ExpectPositionIdFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setPositionId(null);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("positionId", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullHospitalId_ExpectHospitalIdFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setPositionId((long) 1);
        userRegisterForm.setHospitalId(null);
        userRegisterForm.setRoleId((long) 1);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("hospitalId", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
    @Test
    public void validation_nullRoleId_ExpectRoleIdFailValidation() {
        userRegisterForm.setInn(correctInn);
        userRegisterForm.setPassword(correctPassword);
        userRegisterForm.setDocumentNumber(correctDocumentNumber);
        userRegisterForm.setName(correctName);
        userRegisterForm.setSurname(correctSurname);
        userRegisterForm.setMiddleName(correctMiddleName);
        userRegisterForm.setBirthDate(today);
        userRegisterForm.setGender(correctGender);
        userRegisterForm.setPlaceId((long) 1);
        userRegisterForm.setPositionId((long) 1);
        userRegisterForm.setHospitalId((long) 1);
        userRegisterForm.setRoleId(null);

        Set<ConstraintViolation<UserRegisterForm>> violations = validator.validate(userRegisterForm);
        violations.forEach(violation -> assertEquals("Обязательное поле", violation.getMessage()));
        violations.forEach(violation -> assertEquals("roleId", violation.getPropertyPath().toString()));
        assertEquals(1, violations.size());
        assertFalse(violations.isEmpty());
    }
}
