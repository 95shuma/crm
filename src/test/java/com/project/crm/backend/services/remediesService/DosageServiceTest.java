package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.remediesForm.DosageRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public  class DosageServiceTest {

    @InjectMocks
    private DosageService dosageService;

    @Mock
    MeasureRepo measureRepo;

    @Mock
    DosageRepo dosageRepo;


    private DosageRegisterForm dosageRegisterForm;
    private String correctName;
    private String testDosage;
    private String testForm;
    private Integer test;



    @Before
    public void setUp(){
        dosageRegisterForm = new DosageRegisterForm();
        correctName = "арбидол";
        testDosage="250 mg";
        test=5;
    }
    @Test
    public void createDosage_saveCorrectDosage_expectSave(){

        when(measureRepo.findById((long) 1)).thenReturn(Optional.of(Measure.builder()
                .id((long) 1)
                .name(testDosage)
                .build())
        );
        dosageRegisterForm.setName(correctName);
        dosageRegisterForm.setMeasureId((long) 1);
        dosageRegisterForm.setQuantity( test);

        Assertions.assertDoesNotThrow(() -> dosageService.createDosage(dosageRegisterForm));
    }

}