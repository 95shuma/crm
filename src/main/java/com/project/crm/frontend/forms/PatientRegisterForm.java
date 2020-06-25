package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class PatientRegisterForm {

    @Size(min = 14, max = 14, message = "Требуется ввести 14 цифр")
    @NotBlank(message = "Это поле не может быть пустым")
    private String inn = "";

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password = "";

    @NotBlank(message = "Обязательное поле")
    private String documentNumber = "";

    @NotBlank(message = "Обязательное поле")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    private String surname = "";

    @NotBlank(message = "Обязательное поле")
    private String middleName = "";

    @PastOrPresent(message = "Дата рождение должно быть прошлой")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    private Date birthDate;

    @NotBlank(message = "Обязательное поле")
    private String gender = "";

    @NotNull(message = "Обязательное поле")
    private Long placeId;

    @NotNull(message = "Обязательное поле")
    private Long hospitalId;

    @NotNull(message = "Обязательное поле")
    private Long roleId;
}
