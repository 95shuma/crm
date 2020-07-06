package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.repository.RemediesFormRepo;
import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public  class RemediesFormServiceTest {

    @InjectMocks
    private RemediesFormService remediesFormService;
    @Mock
    RemediesFormRepo remediesFormRepo;

    private RemediesFormRegisterForm remediesFormRegisterForm;
    private String test;

    @Before
    public void setUp(){
        remediesFormRegisterForm = new RemediesFormRegisterForm();
        test="tablets";
    }
    @Test
    public void createInt_saveCorrectForm_expectSave(){
        remediesFormRegisterForm.setName(test);
        Assertions.assertDoesNotThrow(() -> remediesFormService.createRemediesForm(remediesFormRegisterForm));
    }

}