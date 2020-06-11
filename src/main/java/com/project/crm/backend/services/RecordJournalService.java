package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordJournalService {

    private final RecordJournalRepo recordJournalRepo;

    private final HospitalService hospitalService;
    private final UserService userService;

    public List<RecordJournal> getAll(){return recordJournalRepo.findAll();}

    public List<RecordJournal> getByDoctor(Long doctor){
        return recordJournalRepo.findByDoctorId(doctor);
    }

    public List<RecordJournal> getByPatient(Long patient){
        return recordJournalRepo.findByPatientId(patient);
    }

    public void createRecordJournal(RecordJournalRegisterForm recordJournalRegisterForm, Principal principal){

        var recordJournal = RecordJournal.builder()
                .doctor(userService.getByInn(recordJournalRegisterForm.getDoctorId()))
                .hospital(hospitalService.getById(recordJournalRegisterForm.getHospitalId()))
                .patient(userService.getByInn(principal.getName()))
                .registrar(userService.getByInn(recordJournalRegisterForm.getDoctorId()))
                .reason(recordJournalRegisterForm.getReason())
                .dateTime(LocalDateTime.now())
                .build();

        recordJournalRepo.save(recordJournal);
    }
}
