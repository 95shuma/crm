package com.project.crm.frontend;

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
    private String document_number = "";

    @NotBlank(message = "Обязательное поле")
    private String full_name = "";

    @NotBlank(message = "Обязательное поле")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    private String surname = "";

    @NotBlank(message = "Не обязательное поле")
    private String middle_name = "";

    @NotBlank(message = "Не обязательное поле")
    private Date birth_date = null;

    @NotBlank(message = "Обязательное поле")
    private String gender = "";

    @NotBlank(message = "Обязательное поле")
    private String registration_place_id = "";

    @NotBlank(message = "Обязательное поле")
    private String hospital_id = "";

}
