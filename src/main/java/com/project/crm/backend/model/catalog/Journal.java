package com.project.crm.backend.model.catalog;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "patient_id")
    private Patient patient;

    @ManyToOne @JoinColumn(name= "hospital_id")
    private Hospital hospital;

    @ManyToOne @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @ManyToOne @JoinColumn(name= "registrar_id")
    private Doctor registrar;

    @ManyToOne @JoinColumn(name= "registration_type_id")
    private RegistrationType registration_type;
    @Column
    private Date dateTime;
    @Column
    private String reason;
}
