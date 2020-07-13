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



}
