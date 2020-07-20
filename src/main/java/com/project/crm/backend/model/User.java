package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

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

    @Column(length = 64)
    private Long inn;

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

    @PastOrPresent(message = "Дата рождение должно быть прошлой")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    @Column
    private Date birthDate;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String gender;

    @ManyToOne @JoinColumn(name = "place_id")
    private Place place;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @OneToMany(mappedBy="user")
    private List<RegistrationJournal> registrationJournals;
}
