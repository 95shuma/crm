package com.project.crm.backend.model.catalog;

import com.project.crm.backend.model.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "registrations_journal")
public class RegistrationJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name= "hospital_id")
    private Hospital hospital;

    @ManyToOne @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne @JoinColumn(name= "position_id")
    private Position position;

    @ManyToOne @JoinColumn(name= "role_id")
    private Role role;
}
