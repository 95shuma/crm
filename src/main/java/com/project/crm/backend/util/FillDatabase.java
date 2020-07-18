package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.*;
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

@Configuration
public class FillDatabase extends RepoMethods {

    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Bean
    @Profile(Constants.PROFILE_ENVIRONMENT_PRODUCTION)
    CommandLineRunner fillDatabaseWithConstantData(UserRepo userRepo, RegistrationJournalRepo registrationJournalRepo,  RoleRepo roleRepo){
        return (args) -> {
            if (roleRepo.findAll().size() == 0){
                saveRolesConstant(roleRepo);
            }
            if (userRepo.findAll().size() == 0){
                saveAdminConstant(Constants.ADMIN_PROD_INN, Constants.ADMIN_PROD_PASSWORD, userRepo, roleRepo, registrationJournalRepo);
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
            saveRemedyTypes(qty, remedyTypeRepo);
            saveRemedyForms(qty, remediesFormRepo);
            savePharmacologicalGroups(qty, pharmacologicalGroupRepo);
            saveMeasures(qty, measureRepo);
            saveInternationalNames(qty, internationalNameRepo);
            saveDosages(qty, dosageRepo, measureRepo);
            savePlaces(qty, placeRepo);
            saveRolesConstant(roleRepo);
            saveHospitals(qty, hospitalRepo, placeRepo);
            savePositionsConstant(positionRepo);
            //--------------------------------------------------- Справочники ---------------------------------------------------
            saveAdminConstant(Constants.ADMIN_DEV_INN, Constants.ADMIN_DEV_PASSWORD, userRepo, roleRepo, registrationJournalRepo);
            saveDoctorsConstant(userRepo, hospitalRepo, roleRepo, positionRepo, registrationJournalRepo);
            saveDoctorsRandomForEachHospital(qty, qty, userRepo, hospitalRepo, positionRepo, roleRepo, registrationJournalRepo);
            savePatientsConstant(userRepo, hospitalRepo, roleRepo, positionRepo, registrationJournalRepo);
            savePatientsRandom(qty, userRepo, hospitalRepo, roleRepo, placeRepo, positionRepo, registrationJournalRepo);
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
                        .doctor(userRepo.findByInn(Long.parseLong(Constants.DOCTOR_INN)).get())
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
                    .doctor(userRepo.findByInn(Long.parseLong(Constants.DOCTOR_INN)).get())
                    .registrar(userRepo.findAllHospitalStaff().get(rn.nextInt(userRepo.findAllHospitalStaff().size())))
                    .patient(userRepo.findByInn(Long.parseLong(Constants.PATIENT_INN)).get())
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



}

