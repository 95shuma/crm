package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.DirectionRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.InstrumExaminationRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.LabExaminationRepo;
import com.project.crm.backend.services.medicalHistoryService.DirectionService;
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
public  class DirectionServiceTest {

    @InjectMocks
    private DirectionService directionService;

    @Mock
    private DirectionRepo directionRepo;

    @Mock
    private InstrumExaminationRepo instrumExaminationRepo;

    @Mock
    private LabExaminationRepo labExaminationRepo;

    @Mock
    private PositionRepo positionRepo;

    @Mock
    private MedicalHistoryRepo medicalHistoryRepo;

    private DirectionRegisterForm directionRegisterForm;

    @Before
    public void setUp(){
        directionRegisterForm = new DirectionRegisterForm();
    }

    @Test
    public void createInt_saveCorrectForm_expectSave(){
        when(instrumExaminationRepo.findById((long) 1)).thenReturn(Optional.of(InstrumExamination.builder()
                .id((long) 1)
                .description("description")
                .rate("5")
                .name("something")
                .build())
        );
        when(labExaminationRepo.findById((long) 1)).thenReturn(Optional.of(LabExamination.builder()
                .id((long) 1)
                .name("testName")
                .rate("5")
                .build())
        );
        when(positionRepo.findById((long) 1)).thenReturn(Optional.of(Position.builder()
                .id((long) 1)
                .name("testName")
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
        directionRegisterForm.setPositionId((long) 1);
        directionRegisterForm.setInstrumExaminationId((long)1);
        directionRegisterForm.setLabExaminationId((long)1);
        directionRegisterForm.setMedicalHistoryId((long)1);

        Assertions.assertDoesNotThrow(() -> directionService.createDirection(directionRegisterForm));
    }

}