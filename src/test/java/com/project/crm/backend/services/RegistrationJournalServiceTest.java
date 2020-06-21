package com.project.crm.backend.services;

import com.project.crm.backend.model.User;
import com.project.crm.backend.repository.*;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationJournalServiceTest {
    @Autowired
    private RegistrationJournalService registrationJournalService;

    @MockBean
    private  RegistrationJournalRepo registrationJournalRepo;

    @Test
    void createRegistrationJournal() {
        User user = new User();

        UserRegisterForm admin = new UserRegisterForm();
        UserRegisterForm doctor = new UserRegisterForm();
        UserRegisterForm seniorDoctor = new UserRegisterForm();
        UserRegisterForm juniorDoctor = new UserRegisterForm();
        UserRegisterForm patient = new UserRegisterForm();

        admin.setRoleId((long)1);
        //------------------------------------------------------
        doctor.setRoleId((long)2);
        doctor.setHospitalId((long)2);
        doctor.setPositionId((long)2);
        //------------------------------------------------------
        seniorDoctor.setRoleId((long)3);
        seniorDoctor.setHospitalId((long)3);
        seniorDoctor.setPositionId((long)3);
        //------------------------------------------------------
        juniorDoctor.setRoleId((long)4);
        juniorDoctor.setHospitalId((long)4);
        juniorDoctor.setPositionId((long)4);
        //------------------------------------------------------
        patient.setRoleId((long)5);
        patient.setHospitalId((long)5);
        //------------------------------------------------------

        registrationJournalService.createRegistrationJournal(user,admin);
        registrationJournalService.createRegistrationJournal(user,doctor);
        registrationJournalService.createRegistrationJournal(user,seniorDoctor);
        registrationJournalService.createRegistrationJournal(user,juniorDoctor);
        registrationJournalService.createRegistrationJournal(user,patient);
    }
}