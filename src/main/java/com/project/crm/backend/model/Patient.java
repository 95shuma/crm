package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Journal;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.model.catalog.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Size(min = 14, message = "Требуется ввести 14 цифр")
    @Column(length = 64)
    private String inn;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Size(min = 8, message = "Пароль должен соделжать минимум 8 символов")
    @Column(length = 64)
    private String password;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String document_number;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 256)
    private String full_name;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String name;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String surname;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String middle_name;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    private Date birth_date;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String gender;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    @OneToMany
    private Set<Role> role_id;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    @OneToMany
    private Set<RegistrationPlace> registration_place_id;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    @OneToMany
    private Set<Hospital> hospital_id;

    @OneToMany(mappedBy = "patient")
    Set<Journal> journals;
}
