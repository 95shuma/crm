package com.project.crm.backend.model.catalog;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="registration_types")
public class RegistrationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}