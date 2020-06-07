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
@Table(name = "hospitals_doctor")
public class HospitalsDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Role role;
}
