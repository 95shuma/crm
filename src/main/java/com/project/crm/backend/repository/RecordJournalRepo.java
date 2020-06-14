package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RecordJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordJournalRepo extends JpaRepository<RecordJournal, Long> {

    List<RecordJournal> findByDoctorId(Long id);
    List<RecordJournal> findByPatientId(Long id);
}
