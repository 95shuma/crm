package com.project.crm.backend.repository;

import com.project.crm.backend.model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepo extends JpaRepository <WorkSchedule, Long> {
//    Page<WorkSchedule> findByRegistrationJournalUserInn(Long inn, Pageable pageable);
//    WorkSchedule findByDateAndRegistrationJournalUserInn(LocalDate date,Long inn);
}