package com.project.crm.backend.model.catalog;

import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @Column
    private LocalDateTime dateTime;
}
