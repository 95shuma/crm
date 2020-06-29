package com.project.crm.backend.model.catalog;


import com.project.crm.backend.model.catalog.medicalHistoryCatalog.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "medical_histories")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "record_journal_id")
    private RecordJournal recordJournal;

    private Date date;

    private boolean typeOfVisit;

    private String complaint;

    @ManyToOne @JoinColumn(name= "direction_id")
    private Direction direction;

    @ManyToOne @JoinColumn(name= "examination_result_id")
    private ExaminationResult examinationResult;

    @ManyToOne @JoinColumn(name= "diagnose_result_id")
    private DiagnoseResult diagnoseResult;

    private String recommendation;

    @ManyToOne @JoinColumn(name= "treatment_id")
    private Treatment treatment;

    @ManyToOne @JoinColumn(name= "sick_list_id")
    private SickList sickList;

}
