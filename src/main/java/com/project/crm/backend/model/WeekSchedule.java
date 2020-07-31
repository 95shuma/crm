package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "week_schedule")
public class WeekSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "reg_journal_id")
    private RegistrationJournal registrationJournal;

    private LocalTime timeStart;

    private LocalTime timeEnd;

    private DayOfWeek dayOfWeek;
}
