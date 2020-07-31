package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "reg_journal_id")
    private RegistrationJournal registrationJournal;

    private LocalDateTime dayTimeStart;

    private LocalDateTime dayTimeEnd;
}
