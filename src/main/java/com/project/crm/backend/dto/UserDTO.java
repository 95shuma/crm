package com.project.crm.backend.dto;

import com.project.crm.backend.model.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private Long id;

    @Min(value = 14, message = "Требуется ввести 14 цифр")
    @Max(value = 14, message = "Требуется ввести 14 цифр")
    private Long inn;

    @NotBlank(message = "Не обязательное поле")
    private String documentNumber;

    @NotBlank(message = "Не обязательное поле")
    private String fullName;

    @NotBlank(message = "Не обязательное поле")
    private String name;

    @NotBlank(message = "Не обязательное поле")
    private String surname;

    @NotBlank(message = "Не обязательное поле")
    private String middleName;

    @PastOrPresent(message = "Дата рождение должно быть прошлой")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательное поле")
    private Date birthDate;

    @NotBlank(message = "Не обязательное поле")
    private String gender;

    private PlaceDTO place;


    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .inn(user.getInn())
                .documentNumber(user.getDocumentNumber())
                .fullName(user.getFullName())
                .name(user.getName())
                .surname(user.getSurname())
                .middleName(user.getMiddleName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .place(PlaceDTO.from(user.getPlace()))
                .build();
    }

    public static List<UserDTO> listFrom(List<User> objList){
        List<UserDTO> listDto = new ArrayList<>();
        objList.forEach(obj -> {
            listDto.add(UserDTO.from(obj));
        });
        return listDto;
    }
}
