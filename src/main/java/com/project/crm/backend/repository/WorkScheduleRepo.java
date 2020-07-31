package com.project.crm.backend.repository;

import com.project.crm.backend.model.WeekSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkScheduleRepo extends JpaRepository <WeekSchedule, Long> {
//    Page<WorkSchedule> findByRegistrationJournalUserInn(Long inn, Pageable pageable);
//    WorkSchedule findByDateAndRegistrationJournalUserInn(LocalDate date,Long inn);
}