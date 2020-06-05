package com.project.crm.frontend;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
public class JournalRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Size(min = 14, message = "Требуется ввести 14 цифр")
    private String inn = "";

    private String doctor = "";

    private String hospital = "";

    private String registration_type = "";

    @NotBlank(message = "Обязательное поле")
    private String reason = "";

}
