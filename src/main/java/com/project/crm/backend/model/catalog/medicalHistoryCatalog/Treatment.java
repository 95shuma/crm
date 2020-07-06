package com.project.crm.backend.model.catalog.medicalHistoryCatalog;

import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.Remedy;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "remedy_id")
    private Remedy remedy;

    private String remediesNote;

    @ManyToOne @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    private String procedureNote;

    private boolean type;

    @ManyToOne @JoinColumn(name= "medical_history_id")
    private MedicalHistory medicalHistory;

}