package com.project.crm.backend.services;

import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.repository.HospitalRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.UserRepo;
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
    private final UserRepo userRepo;
    private final HospitalRepo hospitalRepo;

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

        RecordJournal recordJournal;

            if(!recordJournalRegisterForm.getRegistrarId().isEmpty()){
                recordJournal = RecordJournal.builder()
                        .doctor(userRepo.findById(userService.getByInn(recordJournalRegisterForm.getDoctorId()).getId()).get())
                        .hospital(hospitalRepo.findById(hospitalService.getById(recordJournalRegisterForm.getHospitalId()).getId()).get())
                        .patient(userRepo.findById(userService.getByInn(principal.getName()).getId()).get())
                        .registrar(userRepo.findById(userService.getByInn(recordJournalRegisterForm.getRegistrarId()).getId()).get())
                        .reason(recordJournalRegisterForm.getReason())
                        .dateTime(LocalDateTime.now())
                        .build();
            }
            else{
                recordJournal = RecordJournal.builder()
                        .doctor(userRepo.findById(userService.getByInn(recordJournalRegisterForm.getDoctorId()).getId()).get())
                        .hospital(hospitalRepo.findById(hospitalService.getById(recordJournalRegisterForm.getHospitalId()).getId()).get())
                        .patient(userRepo.findById(userService.getByInn(principal.getName()).getId()).get())
                        .reason(recordJournalRegisterForm.getReason())
                        .dateTime(LocalDateTime.now())
                        .build();
            }
        recordJournalRepo.save(recordJournal);
    }
}
