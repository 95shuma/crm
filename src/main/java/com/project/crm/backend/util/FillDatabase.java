package com.project.crm.backend.util;

import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.*;

@Configuration
public class FillDatabase extends RepoMethods {

    private static final Random rn = new Random();
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
                                       ProcedureRepo procedureRepo, SickListRepo sickListRepo, TreatmentRepo treatmentRepo, PasswordResetTokenRepo passwordResetTokenRepo,
                                       WorkScheduleRepo workScheduleRepo){
        return (args) -> {
            examinationRepo.deleteAll();
            diseaseRepo.deleteAll();
            workScheduleRepo.deleteAll();
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

            int qty = rn.nextInt(10)+10;
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
            //--------------------------------------------------- Пользователи ---------------------------------------------------
            saveAdminConstant(Constants.ADMIN_DEV_INN, Constants.ADMIN_DEV_PASSWORD, userRepo, roleRepo, registrationJournalRepo);
            saveDoctorsConstant(userRepo, hospitalRepo, roleRepo, positionRepo, registrationJournalRepo);
            savePatientsConstant(userRepo, hospitalRepo, roleRepo, positionRepo, registrationJournalRepo);
            saveDoctorsRandomForEachHospital(qty, qty, userRepo, hospitalRepo, positionRepo, roleRepo, registrationJournalRepo);
            savePatientsRandom(qty, userRepo, hospitalRepo, roleRepo, placeRepo, positionRepo, registrationJournalRepo);
            saveRandomUsersBasedOnAnotherUserAtTheSameHospital(25, registrationJournalRepo.findFirstByUserInnAndRoleId(Long.parseLong(Constants.SENIOR_DOCTOR_INN), roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId()),
                    roleRepo, positionRepo, hospitalRepo, userRepo, placeRepo, registrationJournalRepo);
            //--------------------------------------------------- Пользователи ---------------------------------------------------
            //--------------------------------------------------- График ---------------------------------------------------------
            //saveWorkScheduleForConstantUser(registrationJournalRepo.findFirstByUserInnAndRoleId(Long.parseLong(Constants.SENIOR_DOCTOR_INN), roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId()), workScheduleRepo);                                               //График нашему АдминуЛПУ
            saveWorkSchedulesForAllRegUsers(roleRepo, workScheduleRepo, registrationJournalRepo);
            //--------------------------------------------------- График ---------------------------------------------------
            saveDiseases(qty, diseaseRepo);
            saveRemedies(qty, remedyRepo, remedyTypeRepo, remediesFormRepo, pharmacologicalGroupRepo, internationalNameRepo, dosageRepo);
            saveExaminations(qty, examinationRepo);
            saveMedicalHistory(qty, medicalHistoryRepo);
            saveRecordJournal(qty, recordJournalRepo, userRepo, medicalHistoryRepo, hospitalRepo);
            //--------------------------------------------------- Для ИБ ---------------------------------------------------//
            saveDiagnoses(qty, diagnoseRepo, positionRepo);
            saveDiagnoseResults(qty, diagnoseResultRepo, diagnoseRepo, medicalHistoryRepo);
            saveLabExaminations(qty, labExaminationRepo);
            saveInstrumExaminations(qty, instrumExaminationRepo);
            saveExaminationResults(qty, examinationResultRepo, instrumExaminationRepo, labExaminationRepo, medicalHistoryRepo);
            saveSickList(qty, sickListRepo, medicalHistoryRepo);
            saveDirections(qty, directionRepo, labExaminationRepo, instrumExaminationRepo, positionRepo, medicalHistoryRepo);
            saveProcedures(qty, procedureRepo);
            saveTreatments(qty, treatmentRepo, remedyRepo, procedureRepo, medicalHistoryRepo);
            //--------------------------------------------------- Для ИБ ---------------------------------------------------//
            System.out.println("|-------------------------------------- Загрузка фикстурными данными завершена -----------------------------------------------|");
        };
    }
}

