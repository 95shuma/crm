package com.project.crm.frontend.forms;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.*;

@SpringBootTest
public class UserRegisterFormValidatorTest {

    @Autowired
    private Validator validator;

    private UserRegisterForm userRegisterForm;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Before
    public void setUpUserRegisterForm(){
        userRegisterForm = new UserRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        userRegisterForm = null;
    }

    @Test
    public void validation_wrongSizeInn_FailValidation() {


    }
}
