package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.*;
import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class FillDatabase {

    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Bean
    @Profile(Constants.PROFILE_ENVIRONMENT_PRODUCTION)
    CommandLineRunner fillDatabaseWithConstantData(UserRepo userRepo, RegistrationJournalRepo registrationJournalRepo,  RoleRepo roleRepo){
        return (args) -> {
            if (roleRepo.findAll().size() == 0){
                String[] roles = {Constants.ROLE_ADMIN, Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_PATIENT};
                for (int i=0; i<roles.length; i++){
                    roleRepo.insertRoleWithId(Long.parseLong(Integer.toString(i+1)), roles[i]);
                }
            }
            if (userRepo.findAll().size() == 0){
                //--<======================== Admin ========================
                String[] adminName = {" ", faker.name().lastName(), faker.name().firstName(), faker.name().lastName()};

                var user = User.builder()
                        .id(Long.parseLong("1"))
                        .inn(Long.parseLong(Constants.ADMIN_MAIN_INN))
                        .password(passwordEncoder.encode(Constants.ADMIN_MAIN_PASSWORD))
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
                        .user(userRepo.findByInn(Long.parseLong(Constants.ADMIN_MAIN_INN)).get())
                        .role(roleRepo.findById((long) 1).get())
                        .build();

                registrationJournalRepo.save(registrationJournal);
                //-->======================== Admin ========================
            }
        };
    }

    @Bean
    @Profile(Constants.PROFILE_ENVIRONMENT_DEVELOPMENT)
    CommandLineRunner fillFullDatabase(UserRepo userRepo, PlaceRepo placeRepo, RoleRepo roleRepo,
                                   HospitalRepo hospitalRepo, RegistrationJournalRepo registrationJournalRepo,
                                   RecordJournalRepo recordJournalRepo, PositionRepo positionRepo, DiseaseRepo diseaseRepo,
                                   RemedyRepo remedyRepo, ExaminationRepo examinationRepo, DosageRepo dosageRepo, InternationalNameRepo internationalNameRepo,
                                   MeasureRepo measureRepo, PharmacologicalGroupRepo pharmacologicalGroupRepo, RemediesFormRepo remediesFormRepo, RemedyTypeRepo remedyTypeRepo,
                                   MedicalHistoryRepo medicalHistoryRepo, DiagnoseRepo diagnoseRepo, DiagnoseResultRepo diagnoseResultRepo, DirectionRepo directionRepo,
                                   ExaminationResultRepo examinationResultRepo, InstrumExaminationRepo instrumExaminationRepo, LabExaminationRepo labExaminationRepo,
                                   ProcedureRepo procedureRepo, SickListRepo sickListRepo, TreatmentRepo treatmentRepo, PasswordResetTokenRepo passwordResetTokenRepo){
        return (args) -> {
            examinationRepo.deleteAll();
            diseaseRepo.deleteAll();
            registrationJournalRepo.deleteAll();
            roleRepo.deleteAll();
            diagnoseResultRepo.deleteAll();
            diagnoseRepo.deleteAll();
            directionRepo.deleteAll();
            examinationResultRepo.deleteAll();
            instrumExaminationRepo.deleteAll();
            labExaminationRepo.deleteAll();
            positionRepo.deleteAll();
            sickListRepo.deleteAll();
            treatmentRepo.deleteAll();
            recordJournalRepo.deleteAll();
            medicalHistoryRepo.deleteAll();
            passwordResetTokenRepo.deleteAll();
            userRepo.deleteAll();
            hospitalRepo.deleteAll();
            placeRepo.deleteAll();
            procedureRepo.deleteAll();
            remedyRepo.deleteAll();
            dosageRepo.deleteAll();
            measureRepo.deleteAll();
            remediesFormRepo.deleteAll();
            remedyTypeRepo.deleteAll();
            internationalNameRepo.deleteAll();
            pharmacologicalGroupRepo.deleteAll();

            int qty = rn.nextInt(20)+10;
            //--------------------------------------------------- Справочники ---------------------------------------------------
            saveRemedyTypes(remedyTypeRepo, qty);
            saveRemedyForms(remediesFormRepo, qty);
            savePharmacologicalGroups(pharmacologicalGroupRepo, qty);
            saveMeasures(measureRepo, qty);
            saveInternationalNames(internationalNameRepo, qty);
            saveDosages(dosageRepo, measureRepo, qty);
            savePlaces(placeRepo, qty);
            saveRoles(roleRepo);
            saveHospitals(hospitalRepo, placeRepo, qty);
            savePositionsConstant(positionRepo);
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
            //--<======================== MedicalHistory ========================
            List <MedicalHistory> medicalHistories = new ArrayList<>();
            Date date = new Date();
            for (int i = 0; i < qty; i++){
                if(i % 2 == 0){
                    medicalHistories.add(MedicalHistory.builder()
                            .date(date)
                            .typeOfVisit(true)
                            .complaint(faker.music().instrument())
                            .recommendation(faker.lorem().characters())
                            .build());
                }
                else {
                    medicalHistories.add(MedicalHistory.builder()
                            .date(date)
                            .typeOfVisit(false)
                            .complaint(faker.music().instrument())
                            .recommendation(faker.lorem().characters())
                            .build());
                }

            }
            medicalHistoryRepo.saveAll(medicalHistories);
            //-->======================== MedicalHistory ========================
            //--<======================== Record Journal ========================
            List <RecordJournal> recordJournalList = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                recordJournalList.add(RecordJournal.builder()
                        .doctor(userRepo.findAllDoctors().get(rn.nextInt(userRepo.findAllDoctors().size())))
                        .registrar(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                        .patient(userRepo.findAllPatients().get(rn.nextInt(userRepo.findAllPatients().size())))
                        .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                        .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .dateTime(LocalDateTime.now())
                        .dateTimeNow(LocalDateTime.now())
                        .reason(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())).getComplaint())
                        .build()
                );
            }
            for (int i = 0; i < 5; i++){
                recordJournalList.add(RecordJournal.builder()
                        .doctor(userRepo.findByInn(33333333333333L).get())
                        .registrar(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                        .patient(userRepo.findAllPatients().get(rn.nextInt(userRepo.findAllPatients().size())))
                        .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                        .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                        .dateTime(LocalDateTime.now())
                        .dateTimeNow(LocalDateTime.now())
                        .reason(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())).getComplaint())
                        .build()
                );
            }
            recordJournalList.add(RecordJournal.builder()
                    .doctor(userRepo.findByInn(33333333333333L).get())
                    .registrar(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                    .patient(userRepo.findByInn(55555555555555L).get())
                    .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                    .hospital(hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())))
                    .dateTime(LocalDateTime.now())
                    .dateTimeNow(LocalDateTime.now())
                    .reason(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())).getComplaint())
                    .build()
            );
            recordJournalRepo.saveAll(recordJournalList);
            //-->======================== Record Journal ========================
            //--------------------------------------------------- Для ИБ ---------------------------------------------------//
            //--<======================== Diagnose ========================
            List <Diagnose> diagnoses = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                diagnoses.add(Diagnose.builder()
                        .isdCode(faker.superhero().name())
                        .name(faker.book().title())
                        .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                        .build());

            }
            diagnoseRepo.saveAll(diagnoses);
            //-->======================== Diagnose ========================
            //--<======================== DiagnoseResult ========================
            List <DiagnoseResult> diagnoseResults = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                if (i % 2 == 0){
                    diagnoseResults.add(DiagnoseResult.builder()
                            .diagnose(diagnoseRepo.findAll().get(rn.nextInt(diagnoseRepo.findAll().size())))
                            .state(true)
                            .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                            .build());
                }
                else {
                    diagnoseResults.add(DiagnoseResult.builder()
                            .diagnose(diagnoseRepo.findAll().get(rn.nextInt(diagnoseRepo.findAll().size())))
                            .state(false)
                            .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                            .build());
                }

            }
            diagnoseResultRepo.saveAll(diagnoseResults);
            //-->======================== DiagnoseResult ========================
            //--<======================== LabExamination ========================
            List <LabExamination> labExaminations = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                labExaminations.add(LabExamination.builder()
                        .name(faker.superhero().name())
                        .rate(faker.book().title())
                        .build());

            }
            labExaminationRepo.saveAll(labExaminations);
            //-->======================== LabExamination ========================
            //--<======================== InstrumExamination ========================
            List <InstrumExamination> instrumExaminations = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                instrumExaminations.add(InstrumExamination.builder()
                        .name(faker.superhero().name())
                        .rate(faker.superhero().power())
                        .description(faker.superhero().descriptor())
                        .build());

            }
            instrumExaminationRepo.saveAll(instrumExaminations);
            //-->======================== InstrumExamination ========================
            //--<======================== ExaminationResult ========================
            List <ExaminationResult> examinationResults = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                examinationResults.add(ExaminationResult.builder()
                        .labExamination(labExaminationRepo.findAll().get(rn.nextInt(labExaminationRepo.findAll().size())))
                        .instrumExamination(instrumExaminationRepo.findAll().get(rn.nextInt(instrumExaminationRepo.findAll().size())))
                        .labExaminationResult(faker.harryPotter().character())
                        .instrumExaminationResult(faker.harryPotter().location())
                        .generalState(faker.university().name())
                        .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                        .build());

            }
            examinationResultRepo.saveAll(examinationResults);
            //-->======================== ExaminationResult ========================
            //--<======================== SickList ========================
            List <SickList> sickLists = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                sickLists.add(SickList.builder()
                        .number((long)faker.number().numberBetween(10, 30))
                        .startDate(date)
                        .endDate(date)
                        .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                        .build());

            }
            sickListRepo.saveAll(sickLists);
            //-->======================== SickList ========================
            //--<======================== Direction ========================
            List <Direction> directions = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                directions.add(Direction.builder()
                        .labExamination(labExaminationRepo.findAll().get(rn.nextInt(labExaminationRepo.findAll().size())))
                        .instrumExamination(instrumExaminationRepo.findAll().get(rn.nextInt(instrumExaminationRepo.findAll().size())))
                        .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                        .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                        .build());

            }
            directionRepo.saveAll(directions);
            //-->======================== Direction ========================
            //--<======================== Procedure ========================
            List <Procedure> procedures = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                procedures.add(Procedure.builder()
                        .name(faker.pokemon().name())
                        .description(faker.pokemon().location())
                        .build());

            }
            procedureRepo.saveAll(procedures);
            //-->======================== Procedure ========================
            //--<======================== Treatment ========================
            List <Treatment> treatments = new ArrayList<>();
            for (int i = 0; i < qty; i++){
                if (i % 2 == 0){
                    treatments.add(Treatment.builder()
                            .remedy(remedyRepo.findAll().get(rn.nextInt(remedyRepo.findAll().size())))
                            .remediesNote(faker.book().title())
                            .procedure(procedureRepo.findAll().get(rn.nextInt(procedureRepo.findAll().size())))
                            .procedureNote(faker.book().title())
                            .remediesNote(faker.book().author())
                            .type(true)
                            .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                            .build());
                }
                else {
                    treatments.add(Treatment.builder()
                            .remedy(remedyRepo.findAll().get(rn.nextInt(remedyRepo.findAll().size())))
                            .remediesNote(faker.book().title())
                            .procedure(procedureRepo.findAll().get(rn.nextInt(procedureRepo.findAll().size())))
                            .procedureNote(faker.book().title())
                            .remediesNote(faker.book().author())
                            .type(false)
                            .medicalHistory(medicalHistoryRepo.findAll().get(rn.nextInt(medicalHistoryRepo.findAll().size())))
                            .build());
                }

            }
            treatmentRepo.saveAll(treatments);
            //-->======================== Treatment ========================

            //--------------------------------------------------- Для ИБ ---------------------------------------------------//

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
            return "Мужской";
        else
            return "Женский";
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


    // --< ========================================= SAVE методы =========================================

    public static void saveRemedyTypes(RemedyTypeRepo remedyTypeRepo, int qty) {
        List <RemedyType> typeList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            typeList.add(RemedyType.builder()
                    .name(faker.name().title())
                    .build()
            );
        }
        remedyTypeRepo.saveAll(typeList);
    }

    public static void saveRemedyForms(RemediesFormRepo remediesFormRepo, int qty) {
        List <RemediesForm> formList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            formList.add(RemediesForm.builder()
                    .name(faker.name().nameWithMiddle())
                    .build()
            );
        }
        remediesFormRepo.saveAll(formList);
    }
    public static void savePharmacologicalGroups(PharmacologicalGroupRepo pharmacologicalGroupRepo, int qty) {
        List <PharmacologicalGroup> groupList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            groupList.add(PharmacologicalGroup.builder()
                    .name(faker.pokemon().name())
                    .build()
            );
        }
        pharmacologicalGroupRepo.saveAll(groupList);
    }
    public static void saveMeasures(MeasureRepo measureRepo, int qty) {
        List <Measure> measureList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            measureList.add(Measure.builder()
                    .name(faker.name().prefix())
                    .build()
            );
        }
        measureRepo.saveAll(measureList);
    }
    public static void saveInternationalNames(InternationalNameRepo internationalNameRepo, int qty) {
        List <InternationalName> intNameList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            intNameList.add(InternationalName.builder()
                    .name(faker.ancient().god())
                    .build()
            );
        }
        internationalNameRepo.saveAll(intNameList);
    }
    public static void saveDosages(DosageRepo dosageRepo, MeasureRepo measureRepo, int qty) {
        List <Dosage> dosages = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            dosages.add(Dosage.builder()
                    .name(faker.company().name())
                    .measure(measureRepo.findAll().get(rn.nextInt(measureRepo.findAll().size())))
                    .quantity(faker.number().numberBetween(10, 30))
                    .build()
            );
        }
        dosageRepo.saveAll(dosages);
    }
    public static void saveDosagesWithMeasureRepo(DosageRepo dosageRepo, MeasureRepo measureRepo, int qty) {
        saveMeasures(measureRepo, qty);
        saveDosages(dosageRepo, measureRepo, qty);
    }
    public static void savePlaces(PlaceRepo placeRepo, int size) {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            placeList.add(Place.builder()
                    .name(faker.address().fullAddress())
                    .codePlace((long) faker.number().numberBetween(10, 30))
                    .groupCode((long) faker.number().numberBetween(1, 5))
                    .build()
            );
        }
        placeRepo.saveAll(placeList);
    }
    public static void saveRoles(RoleRepo roleRepo){
        String[] roles = {Constants.ROLE_ADMIN, Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_PATIENT};
        for (int i=0; i<roles.length; i++){
            roleRepo.insertRoleWithId(Long.parseLong(Integer.toString(i+1)), roles[i]);
        }
    }
    public static void saveHospitals(HospitalRepo hospitalRepo, PlaceRepo placeRepo,  int qty){
        List<Hospital> hospitalList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            hospitalList.add(Hospital.builder()
                    .name(faker.company().name())
                    .place(placeRepo.findAll().get(rn.nextInt(placeRepo.findAll().size())))
                    .address(faker.address().streetAddress())
                    .build()
            );
        }
        hospitalRepo.saveAll(hospitalList);
    }
    public static void saveHospitalsWithPlaces(HospitalRepo hospitalRepo, PlaceRepo placeRepo,  int qty){
        savePlaces(placeRepo, qty);
        saveHospitals(hospitalRepo, placeRepo, qty);
    }
    public static void savePositionsConstant(PositionRepo positionRepo){
        String[] positions = {"терапевт", "кардиолог", "лор", "детский врач", "офтальмолог"};
        for (int i=0; i<positions.length; i++){
            positionRepo.insertPositionWithId(Long.parseLong(Integer.toString(i+1)), positions[i]);
        }
    }
    public static void savePositionsRandom(PositionRepo positionRepo, int qty){
        List<Position> positionList = new ArrayList<>();
        for (int i=0; i<qty; i++){
            positionList.add(Position.builder()
                    .name(faker.job().position())
                    .build()
            );
        }
        positionRepo.saveAll(positionList);
    }
    // --> ========================================= SAVE методы =========================================
    // --< ========================================= DELETE методы =========================================
    public static void deleteAllData(UserRepo userRepo, PlaceRepo placeRepo, RoleRepo roleRepo,
                                     HospitalRepo hospitalRepo, RegistrationJournalRepo registrationJournalRepo,
                                     RecordJournalRepo recordJournalRepo, PositionRepo positionRepo, DiseaseRepo diseaseRepo,
                                     RemedyRepo remedyRepo, ExaminationRepo examinationRepo, DosageRepo dosageRepo, InternationalNameRepo internationalNameRepo,
                                     MeasureRepo measureRepo, PharmacologicalGroupRepo pharmacologicalGroupRepo, RemediesFormRepo remediesFormRepo, RemedyTypeRepo remedyTypeRepo,
                                     MedicalHistoryRepo medicalHistoryRepo, DiagnoseRepo diagnoseRepo, DiagnoseResultRepo diagnoseResultRepo, DirectionRepo directionRepo,
                                     ExaminationResultRepo examinationResultRepo, InstrumExaminationRepo instrumExaminationRepo, LabExaminationRepo labExaminationRepo,
                                     ProcedureRepo procedureRepo, SickListRepo sickListRepo, TreatmentRepo treatmentRepo, PasswordResetTokenRepo passwordResetTokenRepo){
        examinationRepo.deleteAll();
        diseaseRepo.deleteAll();
        registrationJournalRepo.deleteAll();
        roleRepo.deleteAll();
        diagnoseResultRepo.deleteAll();
        diagnoseRepo.deleteAll();
        directionRepo.deleteAll();
        examinationResultRepo.deleteAll();
        instrumExaminationRepo.deleteAll();
        labExaminationRepo.deleteAll();
        positionRepo.deleteAll();
        sickListRepo.deleteAll();
        treatmentRepo.deleteAll();
        recordJournalRepo.deleteAll();
        medicalHistoryRepo.deleteAll();
        passwordResetTokenRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        placeRepo.deleteAll();
        procedureRepo.deleteAll();
        remedyRepo.deleteAll();
        dosageRepo.deleteAll();
        measureRepo.deleteAll();
        remediesFormRepo.deleteAll();
        remedyTypeRepo.deleteAll();
        internationalNameRepo.deleteAll();
        pharmacologicalGroupRepo.deleteAll();
    }
    // --> ========================================= DELETE методы =========================================
}

