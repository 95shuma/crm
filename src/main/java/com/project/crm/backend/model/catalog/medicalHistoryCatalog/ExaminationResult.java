package com.project.crm.backend.model.catalog.medicalHistoryCatalog;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.Remedy;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="examination_results")
public class ExaminationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "lab_examination_id")
    private LabExamination labExamination;

    private String labExaminatioResult;

    @ManyToOne @JoinColumn(name = "instrum_examination_id")
    private InstrumExamination instrumExamination;

    private String instrumExaminationResult;

    private String generalState;

    @ManyToOne @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;
}