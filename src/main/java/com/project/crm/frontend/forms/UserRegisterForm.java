package com.project.crm.frontend.forms;

import com.project.crm.backend.annotation.UniqueInn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class UserRegisterForm {

    @Size(min = 14, max = 14, message = "Требуется ввести 14 цифр")
    @Pattern(regexp="^([{1}1|2])\\d+$", message = "ИНН состоит только из цифр начинается с 1 или 2 : ${validatedValue}")
    @NotBlank(message = "Это поле не может быть пустым")
    @UniqueInn
    private String inn = "";

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password = "";

    @NotBlank(message = "Обязательное поле")
    @Size(min = 9, max = 9, message = "Требуется ввести 9 значений без пробела")
    @Pattern(regexp="^([{2}ID|AN]).{7}\\d+$", message = "№ докумета начинается с AN или ID и состоит из 7 цифр : ${validatedValue}")
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

    @Past(message = "Дата рождения не может быть в будущем времени")
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    private Date birthDate;

    @NotBlank(message = "Обязательное поле")
    private String gender = "";

    @NotNull(message = "Обязательное поле")
    private Long placeId;

    @NotNull(message = "Обязательное поле")
    private Long positionId;

    @NotNull(message = "Обязательное поле")
    private Long hospitalId;

    @NotNull(message = "Обязательное поле")
    private Long roleId;
}
