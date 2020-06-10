package com.project.crm.backend.dto;

import com.project.crm.backend.model.User;
import lombok.*;

import java.util.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {
    private Long id;
    private String inn;
    private String documentNumber;
    private String fullName;
    private String name;
    private String surname;
    private String middleName;
    private Date birthDate;
    private String gender;
    private PlaceDTO place;


    static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .inn(user.getInn())
                .documentNumber(user.getDocumentNumber())
                .fullname(user.getFullName())
                .name(user.getName())
                .surname(user.getSurname())
                .middleName(user.getMiddleName())
                .date(user.getBirthDate())
                .gender(user.getGender())
                .place(PlaceDTO.from(user.getPlace()))
                .build();
    }
}
