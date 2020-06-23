package com.project.crm.backend.model.catalog;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Обязательное поле")
    private Integer codePlace;

    @NotNull(message = "Обязательное поле")
    private Integer groupCode;
}