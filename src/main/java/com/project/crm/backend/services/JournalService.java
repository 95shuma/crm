package com.project.crm.backend.services;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.repository.JournalRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JournalService {

    @Autowired
    JournalRepo journalRepo;

    public List<Journal> getByDoctor(Long doctor){
        return journalRepo.findByDoctor_Id(doctor);
    }

    public List<Journal> getByPatient(Long patient){
        return journalRepo.findByPatient_Id(patient);
    }

    public void save(Journal journal){
        journalRepo.save(journal);
    }
}
