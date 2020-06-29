package com.project.crm.backend.model.catalog.medicalHistoryCatalog;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Position;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="directions")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "lab_examination_id")
    private LabExamination labExamination;

    @ManyToOne @JoinColumn(name = "instrum_examination_id")
    private InstrumExamination instrumExamination;

    @ManyToOne @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne @JoinColumn(name= "medical_history_id")
    private MedicalHistory medicalHistory;

}