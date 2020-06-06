package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.project.crm.backend.model.Administrator;
import com.project.crm.backend.model.Doctor;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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
            for (int i = 0; i < qty; i++){
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
            for (String position: positions){
                positionList.add(Position.builder()
                        .name(position)
                        .build()
                );
            }
            for (int i = 0; i < qty; i++){
                positionList.add(Position.builder()
                        .name(faker.job().position())
                        .build()
                );
            }
            positionRepo.saveAll(positionList);
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
            for (int i = 0; i < qty; i++){
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
                        .dateTime(faker.date().past(10, TimeUnit.DAYS))
                        .reason(faker.superhero().name())
                        .build()
                );
            }
            journalRepo.saveAll(journalList);
            //-->======================== Journal ========================
            //--<======================== hospital_doctor ========================
            List <HospitalsDoctor> hospitalsDoctorsList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                hospitalsDoctorsList.add(HospitalsDoctor.builder()
                        .doctor(doctorRepo.findAll().get(rn.nextInt(doctorRepo.findAll().size())))
                        .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                        .role(roleRepo.findRoleById(Long.parseLong(getAnyDoctorRole())))     //2 4 5
                        .build()
                );
            }
            hospitalsDoctorRepo.saveAll(hospitalsDoctorsList);
            //-->======================== hospital_doctor ========================

        };
    }
}
