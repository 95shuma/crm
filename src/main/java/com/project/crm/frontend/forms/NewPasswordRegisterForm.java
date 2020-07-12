package com.project.crm.frontend.forms;

import com.project.crm.backend.model.PasswordResetToken;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class NewPasswordRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String firstPassword;

    private String userInn;

    private String token;

}
