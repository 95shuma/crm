package com.project.crm.backend.dto;

import com.project.crm.backend.model.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        objList.stream().forEach(obj -> {
            listDto.add(UserDTO.from(obj));
        });
        return listDto;
    }
}
