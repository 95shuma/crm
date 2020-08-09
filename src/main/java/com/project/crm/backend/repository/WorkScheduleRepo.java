package com.project.crm.backend.repository;

import com.project.crm.backend.model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkScheduleRepo extends JpaRepository <WorkSchedule, Long> {
//    Page<WorkSchedule> findByRegistrationJournalUserInn(Long inn, Pageable pageable);
//    WorkSchedule findByDateAndRegistrationJournalUserInn(LocalDate date,Long inn);
    List<WorkSchedule> findAllByRegistrationJournalId (Long id);
}