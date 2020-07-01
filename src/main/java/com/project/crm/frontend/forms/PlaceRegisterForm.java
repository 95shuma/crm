package com.project.crm.frontend.forms;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PlaceRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";


    @Size(min = 14, max = 14, message = "Требуется ввести код из 14 цифр")
    @Pattern(regexp="^\\d+$", message = "Код состоит только из цифр : ${validatedValue}")
    @NotBlank(message = "Это поле не может быть пустым")
    private String codePlace = "";

    @NotNull(message = "Обязательное поле")
    private Long groupCode = null;

}
