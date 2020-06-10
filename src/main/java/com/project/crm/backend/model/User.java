package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.Place;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
public class User {

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
    private String documentNumber;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 256)
    private String fullName;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String surname;

    @NotBlank(message = "Не обязательное поле")
    @Column(length = 64)
    private String middleName;

    @NotBlank(message = "Обязательное поле")
    @Column
    private Date birthDate;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String gender;

    @NotBlank(message = "Обязательное поле")
    @ManyToOne @JoinColumn(name = "place_id")
    private Place place;

    @Column
    @Builder.Default
    private boolean enabled = true;
}