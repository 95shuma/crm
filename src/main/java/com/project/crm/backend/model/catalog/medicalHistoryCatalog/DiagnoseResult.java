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
@Table(name="diagnose_results")
public class DiagnoseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    private boolean state;

    @ManyToOne @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;
}