package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.repository.AdministratorRepo;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import com.project.crm.backend.repository.RoleRepo;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class PreloadDatabaseWithData {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    private static final Random rn = new Random();
    private static final Faker faker = new Faker();
    private static final FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService()
    );
    private String  getRandomGender(){
        if (rn.nextInt(2) == 0)
            return "male";
        else
            return "female";
    }

    @Bean
    CommandLineRunner fillDatabase(AdministratorRepo administratorRepo, DoctorRepo doctorRepo, PatientRepo patientRepo){
        return (args) -> {
            administratorRepo.deleteAll();
            doctorRepo.deleteAll();
            patientRepo.deleteAll();
            //--<======================== Admin ========================
            String[] adminName = {" ", faker.name().lastName(), faker.name().firstName(), faker.name().lastName()};
            administratorRepo.save(Administrator.builder()
                    .id(Long.parseLong("1"))
                    .inn("admin")
                    .password(passwordEncoder.encode("admin"))
                    .document_number("ID".concat(faker.number().digits(7)))
                    .full_name(adminName[1] + adminName[0] + adminName[2] + adminName[0] + adminName[3])
                    .surname(adminName[1])
                    .name(adminName[2])
                    .middle_name(adminName[3])
                    .birth_date(faker.date().birthday())
                    .gender(getRandomGender())
                    .role(roleRepo.findRoleById(Long.parseLong("1")))
                    .enabled(true)
                    .build()
            );
            //--<======================== Admin ========================
            //--<======================== Doctor ========================
            /*List <Doctor> doctorList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(4,7); i++){
                doctorList.add(Doctor.builder()
                        .id(Long.parseLong("1"))
                        .inn("admin")
                        .password(passwordEncoder.encode("admin"))
                        .document_number(faker.lorem().fixedString(2).concat(faker.number().digits(7)))
                        .full_name(faker.name().fullName())
                        .surname(faker.name().lastName())
                        .name(faker.name().firstName())
                        .middle_name(faker.name().lastName())
                        .birth_date(faker.date().birthday())
                        .gender("male")
                        .enabled(true)
                        .build()
                );
            }

            doctorRepo.saveAll(doctorList);*/
            //--<======================== Doctor ========================
            //--<======================== Patient ========================
            List <Patient> patientList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(4,7); i++){
                patientList.add(Patient.builder()
                        .inn(faker.number().digits(14))
                        .password(passwordEncoder.encode("123"))
                        .document_number(faker.lorem().fixedString(2).concat(faker.number().digits(7)))
                        .full_name(faker.name().fullName())
                        .surname(faker.name().lastName())
                        .name(faker.name().firstName())
                        .middle_name(faker.name().lastName())
                        .birth_date(faker.date().birthday())
                        .gender(getRandomGender())
                        .role(roleRepo.findRoleById(Long.parseLong("3")))
                        .enabled(true)
                        .build()
                );
            }

            patientRepo.saveAll(patientList);
            //--<======================== Patient ========================
        };
    }
}
