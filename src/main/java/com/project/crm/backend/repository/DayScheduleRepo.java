package com.project.crm.backend.repository;

import com.project.crm.backend.model.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepo extends JpaRepository <DaySchedule, Long> {

}