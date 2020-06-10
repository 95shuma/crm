package com.project.crm.backend.dto;

import com.project.crm.backend.model.catalog.RecordJournal;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordJournalDTO {
    private Long id;
    private UserDTO doctor;
    private UserDTO patient;
    private UserDTO registrar;
    private HospitalDTO hospital;
    private LocalDateTime dateTime;
    private String reason;

    static RecordJournalDTO from(RecordJournal recordJournal) {
        return builder()
                .id(recordJournal.getId())
                .doctor(UserDTO.from(recordJournal.getDoctor()))
                .patient(UserDTO.from(recordJournal.getPatient()))
                .registrar(UserDTO.from(recordJournal.getRegistrar()))
                .hospital(HospitalDTO.from(recordJournal.getHospital()))
                .dateTime(recordJournal.getDateTime())
                .reason(recordJournal.getReason())
                .build();
    }
}