package com.project.crm.backend.model.catalog;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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
    private Long role_id;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    private Long registration_place_id;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    private Long hospital_id;

}
