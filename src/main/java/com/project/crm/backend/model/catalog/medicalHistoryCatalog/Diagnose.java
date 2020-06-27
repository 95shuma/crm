package com.project.crm.backend.model.catalog.medicalHistoryCatalog;

import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.Position;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isdCode;

    @NotBlank(message = "Обязательное поле")
    private String name;

    @ManyToOne @JoinColumn(name = "position_id")
    private Position position;
}