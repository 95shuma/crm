package com.project.crm.backend.dto;


import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationJournalDTO {
    private Long id;
    private HospitalDTO hospital;
    private UserDTO user;
    private PositionDTO position;
    private RoleDTO role;

    public static RegistrationJournalDTO from(RegistrationJournal registrationJournal) {
        if(registrationJournal.getPosition() != null){
            return builder()
                    .id(registrationJournal.getId())
                    .hospital(HospitalDTO.from(registrationJournal.getHospital()))
                    .user(UserDTO.from(registrationJournal.getUser()))
                    .position(PositionDTO.from(registrationJournal.getPosition()))
                    .role(RoleDTO.from(registrationJournal.getRole()))
                    .build();
        }else {
            return builder()
                    .id(registrationJournal.getId())
                    .hospital(HospitalDTO.from(registrationJournal.getHospital()))
                    .user(UserDTO.from(registrationJournal.getUser()))
                    .role(RoleDTO.from(registrationJournal.getRole()))
                    .build();
        }
    }

    public static List<RegistrationJournalDTO> listFrom(List<RegistrationJournal> objList){
        List<RegistrationJournalDTO> listDto = new ArrayList<>();
        objList.forEach(obj -> {
            listDto.add(RegistrationJournalDTO.from(obj));
        });
        return listDto;
    }
}
