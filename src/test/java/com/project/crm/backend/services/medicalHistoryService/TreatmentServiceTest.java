package com.project.crm.backend.services.medicalHistoryService;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Remedy;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Procedure;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RemedyRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.ProcedureRepo;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.TreatmentRepo;
import com.project.crm.frontend.forms.medicalHistoryForms.TreatmentRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TreatmentServiceTest {

    @InjectMocks
    private TreatmentService treatmentService;

    @Mock
    TreatmentRepo treatmentRepo;

    @Mock
    RemedyRepo remedyRepo;

    @Mock
    ProcedureRepo procedureRepo;

    @Mock
    MedicalHistoryRepo medicalHistoryRepo;

    private TreatmentRegisterForm treatmentRegisterForm;
    private String test_remedy_note;
    private String test_procedure_note;
    private boolean test_type;

    @Before
    public void setUp(){
        treatmentRegisterForm = new TreatmentRegisterForm();
        test_remedy_note = "орбидол";
        test_procedure_note = "внутримышечно";
        test_type = true;
    }

    @Test
    public void createTreatment_saveCorrectTreatment_expectSave(){

        when(remedyRepo.findById((long) 1)).thenReturn(Optional.of(Remedy.builder()
                .id((long) 1)
                .name(test_remedy_note)
                .build())
        );

        when(procedureRepo.findById((long)1)).thenReturn(Optional.of(Procedure.builder()
                .id((long)1)
                .name(test_procedure_note)
                .build())
        );

        when(medicalHistoryRepo.findById((long)1)).thenReturn(Optional.of(MedicalHistory.builder()
                .id((long)1)
                .typeOfVisit(test_type)
                .build())
        );

        treatmentRegisterForm.setRemedyId((long)1);
        treatmentRegisterForm.setProcedureId((long)1);
        treatmentRegisterForm.setMedicalHistoryId((long)1);

        Assertions.assertDoesNotThrow(()-> treatmentService.createTreatment(treatmentRegisterForm));
    }

}
