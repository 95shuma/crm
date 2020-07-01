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
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp="^\\d+$", message = "Код места должен содержать только цифры : ${validatedValue}")
    @Size(min = 10, max = 10, message = "Требуется ввести код из 10 цифр")//нужно поменять на 14
    private String codePlace = "";

    @NotNull(message = "Обязательное поле")
    private Integer groupCode = null;

}
