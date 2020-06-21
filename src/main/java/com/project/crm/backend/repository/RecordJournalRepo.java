package com.project.crm.backend.repository;

import com.project.crm.backend.model.catalog.RecordJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordJournalRepo extends JpaRepository<RecordJournal, Long> {

    List<RecordJournal> findByDoctorId(Long id);
    List<RecordJournal> findByPatientId(Long id);

    @Query(value = "select * from records_journal as rj where rj.doctor_id = ?1 order by rj.date_time asc", nativeQuery = true)
    Page<RecordJournal> findAllByDoctorId(Long id, Pageable pageable);
}
