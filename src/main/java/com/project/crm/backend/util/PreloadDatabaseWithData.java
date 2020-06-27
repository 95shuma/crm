package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PreloadDatabaseWithData {

    private final PasswordEncoder passwordEncoder;

    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));

    @Bean
    CommandLineRunner fillDatabase(UserRepo userRepo, PlaceRepo placeRepo, RoleRepo roleRepo,
                                   HospitalRepo hospitalRepo,  RegistrationJournalRepo registrationJournalRepo,
                                   RecordJournalRepo recordJournalRepo, PositionRepo positionRepo, DiseaseRepo diseaseRepo,
                                   RemedyRepo remedyRepo, ExaminationRepo examinationRepo, DosageRepo dosageRepo, InternationalNameRepo internationalNameRepo,
                                   MeasureRepo measureRepo, PharmacologicalGroupRepo pharmacologicalGroupRepo, RemediesFormRepo remediesFormRepo, RemedyTypeRepo remedyTypeRepo){
        return (args) -> {
            recordJournalRepo.deleteAll();
            registrationJournalRepo.deleteAll();
            userRepo.deleteAll();
            hospitalRepo.deleteAll();
            placeRepo.deleteAll();
            roleRepo.deleteAll();
            positionRepo.deleteAll();
            remedyRepo.deleteAll();
            dosageRepo.deleteAll();
            internationalNameRepo.deleteAll();
            measureRepo.deleteAll();
            pharmacologicalGroupRepo.deleteAll();
            remediesFormRepo.deleteAll();
            remedyTypeRepo.deleteAll();

            int qty = rn.nextInt(30)+10;

            //--------------------------------------------------- Справочники ---------------------------------------------------
            //--<======================== RemedyType ========================
            List <RemedyType> typeList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                typeList.add(RemedyType.builder()
                        .name(faker.name().title())
                        .build()
                );
            }
            remedyTypeRepo.saveAll(typeList);
            //-->======================== RemedyType ========================

            //--<======================== RemediesForm ========================
            List <RemediesForm> formList = new ArrayList<>();
            for (int i = 0; i < 6; i++){
                formList.add(RemediesForm.builder()
                        .name(faker.name().nameWithMiddle())
                        .build()
                );
            }
            remediesFormRepo.saveAll(formList);
            //-->======================== RemediesForm ========================

            //--<======================== PharmacologicalGroup ========================
            List <PharmacologicalGroup> groupList = new ArrayList<>();
            for (int i = 0; i < 20; i++){
                groupList.add(PharmacologicalGroup.builder()
                        .name(faker.pokemon().name())
                        .build()
                );
            }
            pharmacologicalGroupRepo.saveAll(groupList);
            //-->======================== PharmacologicalGroup ========================

            //--<======================== Measure ========================
            List <Measure> measureList = new ArrayList<>();
            for (int i = 0; i < 10; i++){
                measureList.add(Measure.builder()
                        .name(faker.name().prefix())
                        .build()
                );
            }
            measureRepo.saveAll(measureList);
            //-->======================== Measure ========================

            //--<======================== InternationalName ========================
            List <InternationalName> intNameList = new ArrayList<>();
            for (int i = 0; i < 20; i++){
                intNameList.add(InternationalName.builder()
                        .name(faker.ancient().god())
                        .build()
                );
            }
            internationalNameRepo.saveAll(intNameList);
            //-->======================== InternationalName ========================

            //--<======================== Dosage ========================
            List <Dosage> dosages = new ArrayList<>();
            for (int i = 0; i < 5; i++){
                dosages.add(Dosage.builder()
                        .name(faker.company().name())
                        .measure(measureRepo.findAll().get(rn.nextInt(measureRepo.findAll().size())))
                        .quantity(faker.number().numberBetween(10, 30))
                        .build()
                );
            }
            dosageRepo.saveAll(dosages);
            //-->======================== Dosage ========================

            //--<======================== Place ========================
            List <Place> registrationPlaceList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                registrationPlaceList.add(Place.builder()
                        .name(faker.address().fullAddress())
                        .codePlace(faker.number().numberBetween(10, 30))
                        .groupCode(0)
                        .build()
                );
            }
            placeRepo.saveAll(registrationPlaceList);
            //-->======================== Place ========================
            //--<======================== Roles ========================
            List <Role> roleList = new ArrayList<>();
            String[] roles = {Constants.ADMIN, Constants.SENIOR_DOCTOR, Constants.DOCTOR, Constants.JUNIOR_DOCTOR, Constants.PATIENT};
            for (int i=0; i<roles.length; i++){
                roleRepo.insertRoleWithId(Long.parseLong(Integer.toString(i+1)), roles[i]);
            }
            //-->======================== Roles ========================
            //--<======================== Hospitals ========================
            List <Hospital> hospitalList = new ArrayList<>();
            for (int i = 0; i < 5; i++){
                hospitalList.add(Hospital.builder()
                        .name(faker.company().name())
                        .place(placeRepo.findAll().get(rn.nextInt(placeRepo.findAll().size())))
                        .address(faker.address().streetAddress())
                        .build()
                );
            }
            hospitalRepo.saveAll(hospitalList);
            //-->======================== Hospitals ========================
            //--<======================== Positions ========================
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
            //-->======================== Positions ========================
            //--------------------------------------------------- Справочники ---------------------------------------------------
            //--<======================== Admin ========================
            String[] adminName = {" ", faker.name().lastName(), faker.name().firstName(), faker.name().lastName()};

            var user = User.builder()
                    .id(Long.parseLong("1"))
                    .inn(Long.parseLong("11111111111111"))
                    .password(passwordEncoder.encode("11111111111111"))
                    .documentNumber("ID".concat(faker.number().digits(7)))
                    .fullName(adminName[1] + adminName[0] + adminName[2] + adminName[0] + adminName[3])
                    .surname(adminName[1])
                    .name(adminName[2])
                    .middleName(adminName[3])
                    .birthDate(faker.date().birthday())
                    .gender(getRandomGender())
                    .enabled(true)
                    .build();

            userRepo.save(user);

            var registrationJournal = RegistrationJournal.builder()
                    .user(userRepo.findByInn(Long.parseLong("11111111111111")).get())
                    .role(roleRepo.findById((long) 1).get())
                    .build();

            registrationJournalRepo.save(registrationJournal);
            //-->======================== Admin ========================
            //--<======================== Doctor ========================
            List <User> doctorList = new ArrayList<>();
            for (int i = 0; i < (hospitalRepo.findAll().size() + (hospitalRepo.findAll().size()*5) + (hospitalRepo.findAll().size()*9)); i++){           //Каждому ЛПУ нужен один Админ ЛПУ, //Каждому ЛПУ нужно 5 медработников, //Каждому ЛПУ нужно 9 врачей
                if(i == 0)
                    createUser(doctorList , placeRepo, "22222222222222");
                else if(i == 3)
                    createUser(doctorList , placeRepo, "33333333333333");
                else if(i == 6)
                    createUser(doctorList , placeRepo, "44444444444444");
                else
                    createUser(doctorList , placeRepo, returnUniqueINN(userRepo));
            }
            userRepo.saveAll(doctorList);
            //-->======================== Doctor ========================
            //--<======================== Patient ========================
            List <User> patientList = new ArrayList<>();
            for (int i = 0; i < qty; i++){

                if (i == 0)
                    createUser(patientList, placeRepo, "55555555555555");
                else
                    createUser(patientList, placeRepo, returnUniqueINN(userRepo));
            }

            userRepo.saveAll(patientList);
            //-->======================== Patient ========================
            //--<======================== Disease ========================
            List <Disease> diseaseList = new ArrayList<>();
            for (int i = 0; i < 20; i++){
                diseaseList.add(Disease.builder()
                        .name(faker.gameOfThrones().character())
                        .build()
                );
            }
            diseaseRepo.saveAll(diseaseList);
            //-->======================== Disease ========================
            //--<======================== Remedy ========================
            List <Remedy> remedyList = new ArrayList<>();
            for (int i = 0; i < 30; i++){
                remedyList.add(Remedy.builder()
                        .remedyType(remedyTypeRepo.findAll().get(rn.nextInt(remedyTypeRepo.findAll().size())))
                        .pharmacologicalGroup(pharmacologicalGroupRepo.findAll().get(rn.nextInt(pharmacologicalGroupRepo.findAll().size())))
                        .internationalName(internationalNameRepo.findAll().get(rn.nextInt(internationalNameRepo.findAll().size())))
                        .name((faker.superhero().name()))
                        .dosage(dosageRepo.findAll().get(rn.nextInt(dosageRepo.findAll().size())))
                        .remediesForm(remediesFormRepo.findAll().get(rn.nextInt(remediesFormRepo.findAll().size())))
                        .build()
                );
            }
            remedyRepo.saveAll(remedyList);
            //-->======================== Remedy ========================
            //--<======================== Examination ========================
            List <Examination> examinationList = new ArrayList<>();
            for (int i = 0; i < 20; i++){
                examinationList.add(Examination.builder()
                        .name(faker.esports().player())
                        .build()
                );
            }
            examinationRepo.saveAll(examinationList);
            //-->======================== Examination ========================
            //--<======================== Registration Journal ========================
            List <RegistrationJournal> registrationJournalList = new ArrayList<>();
            //Задаем каждому доктору роль и ЛПУ
                //Каждому ЛПУ нужен один Админ ЛПУ
                //Каждому ЛПУ нужно 5+ медработников
                //Каждому ЛПУ нужно 9+ врачей
            AtomicInteger an = new AtomicInteger(0);
            hospitalRepo.findAll().forEach(hospital -> {
                for (int i = 1; i <= (hospitalRepo.findAll().size() + (hospitalRepo.findAll().size()*5) + (hospitalRepo.findAll().size()*9))/hospitalRepo.findAll().size(); i++){
                    if (i == 1){
                        registrationJournalList.add(RegistrationJournal.builder()
                                .user(doctorList.get(an.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findById((long) 2).get())
                                .build()
                        );
                    } else if (i > 1 && i <=6){
                        registrationJournalList.add(RegistrationJournal.builder()
                                .user(doctorList.get(an.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findById((long) 3).get())
                                .build()
                        );
                    } else {
                        registrationJournalList.add(RegistrationJournal.builder()
                                .user(doctorList.get(an.get()))
                                .hospital(hospital)
                                .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                                .role(roleRepo.findById((long) 4).get())
                                .build()
                        );
                    }
                    an.set(an.get()+1);
                }

            });
            AtomicInteger an2 = new AtomicInteger(0);

            hospitalRepo.findAll().forEach(hospital -> {
                for (int i = 0; i < qty/hospitalRepo.findAll().size(); i++){

                    registrationJournalList.add(RegistrationJournal.builder()
                            .user(patientList.get(an2.get()))
                            .hospital(hospital)
                            .role(roleRepo.findById((long) 5).get())
                            .build());

                    an2.set(an2.get()+1);
                }

            });
            registrationJournalRepo.saveAll(registrationJournalList);
            //-->======================== Registration Journal ========================
            //--<======================== Record Journal ========================
            List <RecordJournal> recordJournalList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                recordJournalList.add(RecordJournal.builder()
                        .doctor(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                        .registrar(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                        .patient(userRepo.findAllPatients().get(rn.nextInt(userRepo.findAllPatients().size())))
                        .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .dateTime(LocalDateTime.now())
                        .reason(faker.superhero().name())
                        .build()
                );
            }
            recordJournalRepo.saveAll(recordJournalList);
            //-->======================== Record Journal ========================

        };
    }

    private void createUser(List<User> users, PlaceRepo placeRepo,String inn){


        users.add(User.builder()
                .inn(Long.parseLong(inn))
                .password(passwordEncoder.encode(inn))
                .documentNumber("ID".concat(faker.number().digits(7)))
                .fullName(faker.name().fullName())
                .surname(faker.name().lastName())
                .name(faker.name().firstName())
                .middleName(faker.name().lastName())
                .birthDate(faker.date().birthday())
                .gender(getRandomGender())
                .place(placeRepo.findAll().get(rn.nextInt(placeRepo.findAll().size())))
                .enabled(true)
                .build()
        );
    }
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
            return "3";
        else
            return "4";
    }

    private String returnUniqueINN(UserRepo userRepo){
        List<String> innList = new ArrayList<>();
        String newInn = faker.number().digits(14);
        AtomicBoolean makeNew = new AtomicBoolean(true);
        userRepo.findAll().forEach(obj -> {
            innList.add(obj.getInn().toString());
        });

        innList.forEach(inn -> {
            if (inn.equals(newInn)){
                makeNew.set(false);
            }
        });
        if (makeNew.get()){
            return newInn;
        } else {
            return returnUniqueINN(userRepo);
        }
    }
}

