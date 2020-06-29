package com.project.crm.backend.model.catalog;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "work_schedule")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne @JoinColumn(name= "reg_journal_id")
    private RegistrationJournal registrationJournal;

    private LocalDateTime dayStart;

    private LocalDateTime dayEnd;

    private LocalDateTime lunchStart;

    private LocalDateTime lunchEnd;
}
