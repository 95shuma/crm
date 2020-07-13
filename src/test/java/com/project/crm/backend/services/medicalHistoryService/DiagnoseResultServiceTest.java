package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import com.project.crm.frontend.forms.medicalHistoryForms.DiagnoseResultRegisterForm;
import com.project.crm.frontend.forms.medicalHistoryForms.DirectionRegisterForm;
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
public  class DiagnoseResultServiceTest {

    @InjectMocks
    private DiagnoseResultService diagnoseResultService;

    @Mock
    private DiagnoseResultRepo diagnoseResultRepo;

    @Mock
    private PositionRepo positionRepo;

    @Mock
    private DiagnoseRepo diagnoseRepo;

    @Mock
    private MedicalHistoryRepo medicalHistoryRepo;

    private DiagnoseResultRegisterForm diagnoseResultRegisterForm;

    @Before
    public void setUp(){
        diagnoseResultRegisterForm = new DiagnoseResultRegisterForm();
    }

    @Test
    public void createInt_saveCorrectForm_expectSave(){
        /*when(positionRepo.findById((long) 1)).thenReturn(Optional.of(Position.builder()
                .id((long) 1)
                .name("testName")
                .build())
        );*/
        when(diagnoseRepo.findById((long) 1)).thenReturn(Optional.of(Diagnose.builder()
                .id((long) 1)
                .position(Position.builder()
                        .id((long) 1)
                        .name("testName")
                        .build())
                .isdCode("5")
                .name("something")
                .build())
        );
        when(medicalHistoryRepo.findById((long) 1)).thenReturn(Optional.of(MedicalHistory.builder()
                .id((long) 1)
                .complaint("testComplaint")
                .date(Calendar.getInstance().getTime())
                .recommendation("testRecomdendation")
                .typeOfVisit(true)
                .build())
        );
        diagnoseResultRegisterForm.setDiagnoseId((long) 1);
        diagnoseResultRegisterForm.setMedicalHistoryId((long)1);
        diagnoseResultRegisterForm.setState(String.valueOf(true));

        Assertions.assertDoesNotThrow(() -> diagnoseResultService.createDiagnoseResult(diagnoseResultRegisterForm));
    }

}