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
    @Pattern(regexp="^\\d+$", message = "ИНН состоит только из цифр : ${validatedValue}")
    @NotBlank(message = "Обязательное поле")
    private String inn = "";

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password = "";

    @NotBlank(message = "Обязательное поле")
    private String documentNumber = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Имя должно содержать только буквы : ${validatedValue}")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Фамилия должна содержать только буквы : ${validatedValue}")
    private String surname = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Отчество должно содержать только буквы : ${validatedValue}")
    private String middleName = "";

    @PastOrPresent(message = "Дата рождения не может быть в будущем времени")
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
