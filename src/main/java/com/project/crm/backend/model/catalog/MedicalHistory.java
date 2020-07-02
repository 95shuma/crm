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

    private Date date;

    private boolean typeOfVisit;

    private String complaint;

    private String recommendation;

}
