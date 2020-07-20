package com.project.crm.backend.services.remediesService;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.InstrumExamination;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.LabExamination;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.ExaminationResultRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.InstrumExaminationRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.LabExaminationRepo;
import com.project.crm.frontend.forms.medicalHistoryForms.ExaminationResultForm;
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
public  class ExaminationResultServiceTest {

    @InjectMocks
    private ExaminationResultService examinationResultService;

    @Mock
    private ExaminationResultRepo examinationResultRepo;

    @Mock
    private InstrumExaminationRepo instrumExaminationRepo;

    @Mock
    private LabExaminationRepo labExaminationRepo;

    @Mock
    private MedicalHistoryRepo medicalHistoryRepo;

    private ExaminationResultForm examinationResultForm;

    @Before
    public void setUp(){
        examinationResultForm = new ExaminationResultForm();
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
        when(medicalHistoryRepo.findById((long) 1)).thenReturn(Optional.of(MedicalHistory.builder()
                .id((long) 1)
                .complaint("testComplaint")
                .date(Calendar.getInstance().getTime())
                .recommendation("testRecomdendation")
                .typeOfVisit(true)
                .build())
        );
        examinationResultForm.setGeneralState("good");
        examinationResultForm.setInstrumExaminationId((long)1);
        examinationResultForm.setInstrumExaminationResult("Something");
        examinationResultForm.setLabExaminationId((long)1);
        examinationResultForm.setLabExaminationResult("Some thing");
        examinationResultForm.setMedicalHistoryId((long)1);

        Assertions.assertDoesNotThrow(() -> examinationResultService.createExaminationResult(examinationResultForm));
    }

}