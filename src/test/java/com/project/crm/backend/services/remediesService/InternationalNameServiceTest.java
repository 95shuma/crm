package com.project.crm.backend.services.remediesService;


        import com.project.crm.backend.repository.*;
        import com.project.crm.frontend.forms.remediesForm.InternationalNameRegisterForm;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.jupiter.api.Assertions;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public  class InternationalNameServiceTest {

    @InjectMocks
    private InternationalNameService internationalNameService;


    @Mock
    InternationalNameRepo internationalNameRepo;


    private InternationalNameRegisterForm internationalNameRegisterForm;
    private String test;



    @Before
    public void setUp(){
        internationalNameRegisterForm = new InternationalNameRegisterForm();
        test="amoks";
    }
    @Test
    public void createInt_saveCorrectIntName_expectSave(){
        internationalNameRegisterForm.setName(test);
       Assertions.assertDoesNotThrow(() -> internationalNameService.createInternationalName(internationalNameRegisterForm));
    }

}