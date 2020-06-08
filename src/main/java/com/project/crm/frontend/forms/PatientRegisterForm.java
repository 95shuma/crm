package com.project.crm.frontend.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
public class PatientRegisterForm {

    @Size(min = 14, message = "Требуется ввести 14 цифр")
    private String inn = "";

    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password = "";

    @NotBlank(message = "Обязательное поле")
    private String documentNumber = "";

    @NotBlank(message = "Обязательное поле")
    private String fullName = "";

    @NotBlank(message = "Обязательное поле")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    private String surname = "";

    @NotBlank(message = "Не обязательное поле")
    private String middleName = "";

    @NotBlank(message = "Не обязательное поле")
    private Date birthDate = null;

    @NotBlank(message = "Обязательное поле")
    private String gender = "";

    @NotBlank(message = "Обязательное поле")
    private String registrationPlaceId = "";

    @NotBlank(message = "Обязательное поле")
    private String hospitalId = "";

}
