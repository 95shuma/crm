package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.SickListRepo;
import com.project.crm.frontend.forms.medicalHistoryForms.SickListRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Calendar;
import java.util.Optional;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public  class SickListServiceTest {

    @InjectMocks
    private SickListService sickListService;

    @Mock
    SickListRepo sickListRepo;

    @Mock
    MedicalHistoryRepo medicalHistoryRepo;

    private SickListRegisterForm sickListRegisterForm;
    private String test;

    @Before
    public void setUp(){
        sickListRegisterForm = new SickListRegisterForm();
        test="1234567";
    }
    @Test
    public void createSickList_saveCorrectSickList_expectSave(){
        when(medicalHistoryRepo.findById((long) 3)).thenReturn(Optional.of(MedicalHistory.builder()
                .id((long)3)
                .complaint("some complain")
                .date(Calendar.getInstance().getTime())
                .recommendation("drink water")
                .typeOfVisit(true)
                .build())
        );
        sickListRegisterForm.setStartDate(Calendar.getInstance().getTime());
        sickListRegisterForm.setEndDate(Calendar.getInstance().getTime());
        sickListRegisterForm.setNumber(test);
        sickListRegisterForm.setMedicalHistoryId((long)3);

        Assertions.assertDoesNotThrow(() -> sickListService.createSickList(sickListRegisterForm));
    }

}