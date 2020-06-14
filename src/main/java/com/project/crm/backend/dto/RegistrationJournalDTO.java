package com.project.crm.backend.dto;


import com.project.crm.backend.model.catalog.RegistrationJournal;
import lombok.*;


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
        return builder()
                .id(registrationJournal.getId())
                .hospital(HospitalDTO.from(registrationJournal.getHospital()))
                .user(UserDTO.from(registrationJournal.getUser()))
                .position(PositionDTO.from(registrationJournal.getPosition()))
                .role(RoleDTO.from(registrationJournal.getRole()))
                .build();
    }
}
