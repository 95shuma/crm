package com.project.crm;

import com.project.crm.backend.services.UserService;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    private UserRegisterForm userRegisterForm;

    @Before
    public void setUp(){
        userRegisterForm = new UserRegisterForm();
    }
    @Test
    public void createUser_saveCorrectUser_expectSave(){

        userService.createUser(userRegisterForm);
    }
}