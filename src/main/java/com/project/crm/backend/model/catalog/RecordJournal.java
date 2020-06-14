package com.project.crm.backend.model.catalog;


import com.project.crm.backend.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "records_journal")
public class RecordJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "doctor_id")
    private User doctor;

    @ManyToOne @JoinColumn(name= "patient_id")
    private User patient;

    @ManyToOne @JoinColumn(name= "registrar_id")
    private User registrar;

    @ManyToOne @JoinColumn(name= "hospital_id")
    private Hospital hospital;

//    @ManyToOne @JoinColumn(name= "registration_type_id")
//    private RegistrationType registrationType;

    private LocalDateTime dateTime;

    @NotBlank(message = "Это поле не может быть пустым")
    private String reason;
}
