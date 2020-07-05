package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.remediesForm.MeasureRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public  class MeasureServiceTest {

    @InjectMocks
    private MeasureService measureService;
    @Mock
    MeasureRepo measureRepo;

    private MeasureRegisterForm measureRegisterForm;
    private String test;

    @Before
    public void setUp(){
        measureRegisterForm = new MeasureRegisterForm();
        test="milligram";
    }
    @Test
    public void createInt_saveCorrectMeasure_expectSave(){
        measureRegisterForm.setName(test);
        Assertions.assertDoesNotThrow(() -> measureService.createMeasure(measureRegisterForm));
    }

}