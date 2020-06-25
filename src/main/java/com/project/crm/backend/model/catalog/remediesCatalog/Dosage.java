package com.project.crm.backend.model.catalog.remediesCatalog;

import com.project.crm.backend.model.catalog.Place;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="dosages")
public class Dosage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    private String name;

    @OneToOne @JoinColumn(name = "measure_id")
    private Measure measure;

    @NotBlank(message = "Обязательное поле")
    private String quantity;

}