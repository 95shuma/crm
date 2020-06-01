package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.Journal;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 14, message = "Требуется ввести 14 цифр")
    @Column(length = 64)
    private String inn;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    @Column(length = 64)
    private String password;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String document_number;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 256)
    private String full_name;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String surname;

    @NotBlank(message = "Не обязательное поле")
    @Column(length = 64)
    private String middle_name;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Date birth_date;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String gender;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Long role_id;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Long registration_place_id;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Long hospital_id;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Long position_id;

    @OneToMany(mappedBy = "doctor")
    Set<Journal> journals;
}
