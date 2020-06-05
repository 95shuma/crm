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


    @ManyToOne @JoinColumn(name= "hospital_id")
    private Hospital hospital;

    @ManyToOne @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @ManyToOne @JoinColumn(name= "position_id")
    private Position position;

    @ManyToOne @JoinColumn(name= "role_id")
    private Role role;
}
