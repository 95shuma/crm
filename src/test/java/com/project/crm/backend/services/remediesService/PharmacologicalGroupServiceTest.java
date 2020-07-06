package com.project.crm.backend.services.remediesService;


import com.project.crm.backend.repository.PharmacologicalGroupRepo;
import com.project.crm.frontend.forms.remediesForm.PharmacologicalGroupRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public  class PharmacologicalGroupServiceTest {

    @InjectMocks
    private PharmacologicalGroupService pharmacologicalGroupService;
    @Mock
    PharmacologicalGroupRepo pharmacologicalGroupRepo;

    private PharmacologicalGroupRegisterForm pharmacologicalGroupRegisterForm;
    private String test;

    @Before
    public void setUp(){
        pharmacologicalGroupRegisterForm = new PharmacologicalGroupRegisterForm();
        test="antivirus";
    }
    @Test
    public void createPharmGroup_expectSave(){
        pharmacologicalGroupRegisterForm.setName(test);
        Assertions.assertDoesNotThrow(() -> pharmacologicalGroupService.createPharmGroup(pharmacologicalGroupRegisterForm));
    }

}