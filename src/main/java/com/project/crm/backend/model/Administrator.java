package com.project.crm.backend.model;

import com.project.crm.backend.model.catalog.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "admins")
public class Administrator {

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
    private String documentNumber;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 256)
    private String fullName;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String name;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String surname;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String middleName;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column
    private Date birthDate;

    @NotBlank(message = "Это поле не должно быть пустым")
    @Column(length = 64)
    private String gender;

    @NotBlank(message = "Это поле не должно быть пустым")
    @ManyToOne
    private Role role;

    @Column
    @Builder.Default
    private boolean enabled = true;

}
