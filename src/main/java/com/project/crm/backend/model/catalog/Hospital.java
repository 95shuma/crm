package com.project.crm.backend.model.catalog;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    private String name;

    @ManyToOne @JoinColumn(name = "place_id")
    private Place place;

    @NotBlank(message = "Обязательное поле")
    private String address;
}