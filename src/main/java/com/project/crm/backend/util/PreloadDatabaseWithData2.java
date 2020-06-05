package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.RegistrationPlace;
import com.project.crm.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class PreloadDatabaseWithData2 {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService()
    );
    private String  getRandomGender(){
        if (rn.nextInt(2) == 0)
            return "male";
        else
            return "female";
    }
    private String returnUniqueINN(AdministratorRepo  administratorRepo, DoctorRepo doctorRepo, PatientRepo patientRepo){
        List<String> innList = new ArrayList<>();
        String newInn = faker.number().digits(14);
        AtomicBoolean makeNew = new AtomicBoolean(true);
        administratorRepo.findAll().stream().forEach(obj -> {
            innList.add(obj.getInn());
        });
        doctorRepo.findAll().stream().forEach(obj -> {
            innList.add(obj.getInn());
        });
        patientRepo.findAll().stream().forEach(obj -> {
            innList.add(obj.getInn());
        });
        innList.stream().forEach(inn -> {
            if (inn.equals(newInn)){
                makeNew.set(false);
            }
        });
        if (makeNew.get()){
            return newInn;
        } else {
            return returnUniqueINN(administratorRepo, doctorRepo, patientRepo);
        }
    }
    //чтобы не было совпадений
    @Bean
    CommandLineRunner fillDatabase(AdministratorRepo administratorRepo, DoctorRepo doctorRepo,
                                   PatientRepo patientRepo,             RegistrationPlaceRepo registrationPlaceRepo,
                                   HospitalRepo hospitalRepo){
        return (args) -> {
            administratorRepo.deleteAll();
            doctorRepo.deleteAll();
            patientRepo.deleteAll();
            hospitalRepo.deleteAll();
            registrationPlaceRepo.deleteAll();


            //--<======================== registration_places ========================
            List <RegistrationPlace> registrationPlaceList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(30, 50); i++){
                registrationPlaceList.add(RegistrationPlace.builder()
                        .name(faker.address().fullAddress())
                        .code_place(faker.number().digits(14))
                        .group_code(0)
                        .build()
                );
            }
            registrationPlaceRepo.saveAll(registrationPlaceList);
            //-->======================== registration_places ========================
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
            List <Doctor> doctorList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(15, 30); i++){
                doctorList.add(Doctor.builder()
                        .inn(returnUniqueINN(administratorRepo, doctorRepo, patientRepo))
                        .password(passwordEncoder.encode("123"))
                        .document_number(faker.lorem().fixedString(2).concat(faker.number().digits(7)))
                        .full_name(faker.name().fullName())
                        .surname(faker.name().lastName())
                        .name(faker.name().firstName())
                        .middle_name(faker.name().lastName())
                        .birth_date(faker.date().birthday())
                        .gender(getRandomGender())
                        .registration_place_id(registrationPlaceRepo.findAll().get(rn.nextInt(registrationPlaceRepo.findAll().size())))
                        .enabled(true)
                        .build()
                );
            }
            doctorRepo.saveAll(doctorList);
            //-->======================== Doctor ========================
            //--<======================== Patient ========================
            List <Patient> patientList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(10, 15); i++){
                patientList.add(Patient.builder()
                        .id(Long.parseLong("1"))
                        .inn(returnUniqueINN(administratorRepo, doctorRepo, patientRepo))
                        .password(passwordEncoder.encode("123"))
                        .document_number("ID".concat(faker.number().digits(7)))
                        .full_name(faker.name().fullName())
                        .surname(faker.name().lastName())
                        .name(faker.name().firstName())
                        .middle_name(faker.name().lastName())
                        .birth_date(faker.date().birthday())
                        .gender(getRandomGender())
                        .role_id(roleRepo.findRoleById(Long.parseLong("3")))
                        .enabled(true)
                        .build()
                );
            }
            patientRepo.saveAll(patientList);
            //--<======================== Patient ========================
            //--<======================== hospital_doctor ========================
            /*List <RegistrationPlace> hospital_doctor = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(30, 50); i++){
                registrationPlaceList.add(RegistrationPlace.builder()
                        .name(faker.address().fullAddress())
                        .code_place(faker.number().digits(14))
                        .groupCode(0)
                        .build()
                );
            }
            registrationPlaceRepo.saveAll(hospital_doctor);*/
            //--<======================== hospital_doctor ========================
            //--<======================== hospitals ========================

            List <Hospital> hospitalList = new ArrayList<>();
            for (int i = 0; i < faker.number().numberBetween(30, 50); i++){
                hospitalList.add(Hospital.builder()
                        .name(faker.company().name())
                        .registrationPlace(registrationPlaceRepo.findAll().get(rn.nextInt(registrationPlaceRepo.findAll().size())))
                        .address(faker.address().streetAddress())
                        .build()
                );
            }
            hospitalRepo.saveAll(hospitalList);
            //--<======================== hospitals ========================

        };
    }
}
