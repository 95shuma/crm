package com.project.crm.backend.services;

import com.project.crm.backend.services.HospitalService;
import com.project.crm.backend.services.RecordJournalService;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RecordJournalServiceTest {

    @Autowired
    private RecordJournalService recordJournalService;

    @Autowired
    private HospitalService hospitalService;

    private RecordJournalRegisterForm recordJournalRegisterForm;

    @BeforeEach
    public void setUp(){
        recordJournalRegisterForm = new RecordJournalRegisterForm();
    }

    @AfterEach
    public void tearDown(){
        recordJournalRegisterForm = null;
    }

    @Test
    public void valid_RecordJournalRegisterForm_Name_Should_Pass_Validation(){

        // Allocate
        recordJournalRegisterForm.setReason("живот");
        recordJournalRegisterForm.setDoctorId(Long.parseLong("33333333333333"));
        recordJournalRegisterForm.setHospitalId(hospitalService.getAll().get(0).getId());

        Principal principal = () -> "55555555555555";

        // Assert
        assertEquals("живот", recordJournalService.createRecordJournal(recordJournalRegisterForm, principal).getReason());
    }

    @Test
    public void invalid_RecordJournalRegisterForm_Name_Should_Fail_Validation(){

        // Allocate
        recordJournalRegisterForm.setReason("");
        recordJournalRegisterForm.setDoctorId(Long.parseLong("33333333333333"));
        recordJournalRegisterForm.setRegistrarId(Long.parseLong("33333333333333"));
        recordJournalRegisterForm.setHospitalId(hospitalService.getAll().get(0).getId());

        Principal principal = () -> "55555555555555";

        // Assert
        assertThrows(ConstraintViolationException.class, () -> recordJournalService.createRecordJournal(recordJournalRegisterForm, principal));
    }
}
