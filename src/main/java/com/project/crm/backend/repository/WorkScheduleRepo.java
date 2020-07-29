package com.project.crm.backend.repository;

import com.project.crm.backend.model.WorkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkScheduleRepo extends JpaRepository <WorkSchedule, Long> {
    Page<WorkSchedule> findByRegistrationJournalUserInn(Long inn, Pageable pageable);
    WorkSchedule findByDateAndRegistrationJournalUserInn(LocalDate date,Long inn);
}