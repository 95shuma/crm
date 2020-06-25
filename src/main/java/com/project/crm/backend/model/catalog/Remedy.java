package com.project.crm.backend.model.catalog;

import com.project.crm.backend.model.catalog.remediesCatalog.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="remedies")
public class Remedy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "type_id")
    private RemedyType remedyType;

    @ManyToOne @JoinColumn(name= "pharm_group_id")
    private PharmacologicalGroup pharmacologicalGroup;

    @ManyToOne @JoinColumn(name= "international_name_id")
    private InternationalName internationalName;

    @NotBlank(message = "Это поле не может быть пустым")
    private String name;

    @ManyToOne @JoinColumn(name= "dosage_id")
    private Dosage dosage;

    @ManyToOne @JoinColumn(name= "form_id")
    private RemediesForm remediesForm;

}
