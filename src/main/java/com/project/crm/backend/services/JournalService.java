package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.repository.JournalRepo;
import com.project.crm.frontend.forms.JournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class JournalService {

    JournalRepo journalRepo;

    private final HospitalService hospitalService;
    private final RegistrationTypeService registrationTypeService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public List<Journal> getByDoctor(Long doctor){
        return journalRepo.findByDoctorId(doctor);
    }

    public List<Journal> getByPatient(Long patient){
        return journalRepo.findByPatientId(patient);
    }

    public void save(JournalRegisterForm journalRegisterForm){
        var journal = Journal.builder()
                .doctor(doctorService.getByInn(journalRegisterForm.getDoctor()))
                .hospital(hospitalService.getByName(journalRegisterForm.getHospital()))
                .patient(patientService.getByInn(journalRegisterForm.getInn()))
                .registrationType(registrationTypeService.getByName(journalRegisterForm.getRegistrationType()))
                .reason(journalRegisterForm.getReason())
                .dateTime(LocalDateTime.now())
                .build();
        journalRepo.save(journal);
    }
}
