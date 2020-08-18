package com.project.crm.backend.repository;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RecordJournal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;


public interface RecordJournalRepo extends JpaRepository<RecordJournal, Long> {

    List<RecordJournal> findByDoctorId(Long id);
    List<RecordJournal> findByPatientInn(Long id);
    Page<RecordJournal> findByPatientInn(Long inn, Pageable pageable);
    Page<RecordJournal> findByPatientId(Long id, Pageable pageable);
    Page<RecordJournal> findAllByDoctorIdOrderByDateTime(Long id, Pageable pageable);
    Page<RecordJournal> findAllByDoctorIdAndDateTimeBetween(Long id, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    List<RecordJournal> findAllByDoctorIdAndDateTimeBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT r.patient FROM RecordJournal r WHERE r.doctor = :user group by r.patient")
    Page <User> findAllAcceptedPatients(@Param("user") User user, Pageable pageable);
}
