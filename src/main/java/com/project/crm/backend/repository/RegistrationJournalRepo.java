package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RegistrationJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationJournalRepo extends JpaRepository<RegistrationJournal, Long> {
    
    List<RegistrationJournal> getAllByUserInn(String inn);
    boolean existsByUserInnAndRoleId(String inn, Long id);
}