package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class PreloadDatabaseWithData {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    private String  getRandomGender(){
        if (rn.nextInt(2) == 0)
            return "male";
        else
            return "female";
    }
    private String  getAnyDoctorRole(){
        int r = rn.nextInt(3)+1;
        if (r == 1)
            return "2";
        if (r == 2)
            return "4";
        else
            return "5";
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
    CommandLineRunner fillDatabase( AdministratorRepo administratorRepo,        DoctorRepo doctorRepo,
                                    PatientRepo patientRepo,                    RegistrationPlaceRepo registrationPlaceRepo,
                                    HospitalRepo hospitalRepo,                  PositionRepo positionRepo,
                                    HospitalsDoctorRepo hospitalsDoctorRepo,    JournalRepo journalRepo,
                                    RegistrationTypeRepo registrationTypeRepo,  RoleRepo roleRepo){
        return (args) -> {
            journalRepo.deleteAll();
            hospitalsDoctorRepo.deleteAll();
            administratorRepo.deleteAll();
            doctorRepo.deleteAll();
            patientRepo.deleteAll();
            hospitalRepo.deleteAll();
            registrationPlaceRepo.deleteAll();
            registrationTypeRepo.deleteAll();
            roleRepo.deleteAll();
            positionRepo.deleteAll();

            int qty = rn.nextInt(30)+10;

            //--------------------------------------------------- Справочники ---------------------------------------------------
            //--<======================== registration_places ========================
            List <RegistrationPlace> registrationPlaceList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                registrationPlaceList.add(RegistrationPlace.builder()
                        .name(faker.address().fullAddress())
                        .code_place(faker.number().digits(14))
                        .group_code(0)
                        .build()
                );
            }
            registrationPlaceRepo.saveAll(registrationPlaceList);
            //-->======================== registration_places ========================
            //--<======================== registration_types ========================
            List <RegistrationType> registrationTypeList = new ArrayList<>();
            String[] registrationTypes = {"по-телефону", "экстренно", "перенаправление", "по записи"};
            for (int i=0; i<registrationTypes.length; i++){
                registrationTypeRepo.insertTypesWithId(Long.parseLong(Integer.toString(i+1)), registrationTypes[i]);
            }
            //-->======================== registration_types ========================
            //--<======================== roles ========================
            List <Role> roleList = new ArrayList<>();
            String[] roles = {"cупер админ", "доктор", "админ ЛПУ", "млад мед персонал", "пациент"};
            for (int i=0; i<roles.length; i++){
                roleRepo.insertRoleWithId(Long.parseLong(Integer.toString(i+1)), roles[i]);
            }
            //-->======================== roles ========================
            //--<======================== hospitals ========================
            List <Hospital> hospitalList = new ArrayList<>();
            for (int i = 0; i < 5; i++){
                hospitalList.add(Hospital.builder()
                        .name(faker.company().name())
                        .registrationPlace(registrationPlaceRepo.findAll().get(rn.nextInt(registrationPlaceRepo.findAll().size())))
                        .address(faker.address().streetAddress())
                        .build()
                );
            }
            hospitalRepo.saveAll(hospitalList);
            //-->======================== hospitals ========================
            //--<======================== positions ========================
            List <Position> positionList = new ArrayList<>();
            String[] positions = {"терапевт", "кардиолог", "лор", "детский врач", "офтальмолог"};
            for (int i=0; i<positions.length; i++){
                positionRepo.insertPositionWithId(Long.parseLong(Integer.toString(i+1)), positions[i]);
            }
            /*for (int i=0; i<qty; i++){
                positionList.add(Position.builder()
                        .name(faker.job().position())
                        .build()
                );
            }
            positionRepo.saveAll(positionList);*/
            //-->======================== positions ========================
            //--------------------------------------------------- Справочники ---------------------------------------------------
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
            //-->======================== Admin ========================
            //--<======================== Doctor ========================
            List <Doctor> doctorList = new ArrayList<>();
            for (int i = 0; i < (hospitalRepo.findAll().size() + (hospitalRepo.findAll().size()*5) + (hospitalRepo.findAll().size()*9)); i++){           //Каждому ЛПУ нужен один Админ ЛПУ, //Каждому ЛПУ нужно 5 медработников, //Каждому ЛПУ нужно 9 врачей
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
            for (int i = 0; i < qty; i++){
                patientList.add(Patient.builder()
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
                        .registration_place_id(registrationPlaceRepo.findAll().get(rn.nextInt(registrationPlaceRepo.findAll().size())))
                        .hospital_id(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .enabled(true)
                        .build()
                );
            }
            patientRepo.saveAll(patientList);
            //-->======================== Patient ========================
            //--<======================== Journal ========================
            List <Journal> journalList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                journalList.add(Journal.builder()
                        .doctor(doctorRepo.findAll().get(rn.nextInt(doctorRepo.findAll().size())))
                        .registrar(doctorRepo.findAll().get(rn.nextInt(doctorRepo.findAll().size())))
                        .registration_type(registrationTypeRepo.findAll().get(rn.nextInt(registrationTypeRepo.findAll().size())))
                        .patient(patientRepo.findAll().get(rn.nextInt(patientRepo.findAll().size())))
                        .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .dateTime(LocalDateTime.now())
                        .reason(faker.superhero().name())
                        .build()
                );
            }
            journalRepo.saveAll(journalList);
            //-->======================== Journal ========================
            //--<======================== hospital_doctor ========================
            List <HospitalsDoctor> hospitalsDoctorsList = new ArrayList<>();
            //Задаем каждому доктору роль и ЛПУ
                //Каждому ЛПУ нужен один Админ ЛПУ
                //Каждому ЛПУ нужно 5+ медработников
                //Каждому ЛПУ нужно 9+ врачей
            AtomicInteger j = new AtomicInteger(0);
            hospitalRepo.findAll().stream().forEach(hospital -> {
                for (int i = 1; i <= (hospitalRepo.findAll().size() + (hospitalRepo.findAll().size()*5) + (hospitalRepo.findAll().size()*9))/hospitalRepo.findAll().size(); i++){
                    if (i == 1){
                        hospitalsDoctorsList.add(HospitalsDoctor.builder()
                                .doctor(doctorList.get(j.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findRoleById(Long.parseLong(Integer.toString(3))))
                                .build()
                        );
                    } else if (i > 1 && i <=6){
                        hospitalsDoctorsList.add(HospitalsDoctor.builder()
                                .doctor(doctorList.get(j.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findRoleById(Long.parseLong(Integer.toString(4))))
                                .build()
                        );
                    } else {
                        hospitalsDoctorsList.add(HospitalsDoctor.builder()
                                .doctor(doctorList.get(j.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findRoleById(Long.parseLong(Integer.toString(2))))
                                .build()
                        );
                    }
                    j.set(j.get()+1);
                }

            });
            hospitalsDoctorRepo.saveAll(hospitalsDoctorsList);
            //-->======================== hospital_doctor ========================

        };
    }
}
