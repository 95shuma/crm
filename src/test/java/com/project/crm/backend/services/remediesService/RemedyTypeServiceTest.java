package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.repository.RemediesFormRepo;
import com.project.crm.backend.repository.RemedyTypeRepo;
import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
import com.project.crm.frontend.forms.remediesForm.RemedyTypeRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public  class RemedyTypeServiceTest {

    @InjectMocks
    private RemedyTypeService remedyTypeService;
    @Mock
    RemedyTypeRepo remedyTypeRepo;

    private RemedyTypeRegisterForm remedyTypeRegisterForm;
    private String test;

    @Before
    public void setUp(){
        remedyTypeRegisterForm = new RemedyTypeRegisterForm();
        test="tablets";
    }
    @Test
    public void createType_expectSave(){
        remedyTypeRegisterForm.setName(test);
        Assertions.assertDoesNotThrow(() -> remedyTypeService.createRemedyType(remedyTypeRegisterForm));
    }

}