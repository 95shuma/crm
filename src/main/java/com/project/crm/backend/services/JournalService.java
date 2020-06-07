package com.project.crm.backend.services;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.model.catalog.RegistrationType;
import com.project.crm.backend.repository.JournalRepo;
import com.project.crm.frontend.forms.JournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JournalService {

    @Autowired
    JournalRepo journalRepo;

    private final HospitalService hospitalService;
    private final RegistrationTypeService registrationTypeService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public List<Journal> getByDoctor(Long doctor){
        return journalRepo.findByDoctor_Id(doctor);
    }

    public List<Journal> getByPatient(Long patient){
        return journalRepo.findByPatient_Id(patient);
    }

    public void save(JournalRegisterForm journalRegisterForm){
        var journal = Journal.builder()
                .doctor(doctorService.getByInn(journalRegisterForm.getDoctor()))
                .hospital(hospitalService.getByName(journalRegisterForm.getHospital()))
                .patient(patientService.getByInn(journalRegisterForm.getInn()))
                .registration_type(registrationTypeService.getByName(journalRegisterForm.getRegistration_type()))
                .reason(journalRegisterForm.getReason())
                .dateTime(LocalDateTime.now())
                .build();
        journalRepo.save(journal);
    }
}
