package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class RepoMethods {
    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // --< ========================================= SAVE методы =========================================

    public static void saveRemedyTypes(RemedyTypeRepo remedyTypeRepo, int qty) {
        List<RemedyType> typeList = new ArrayList<>();
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
    public static void saveAdminByInnAndPassword(String inn, String password, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        String[] adminName = {" ", faker.name().lastName(), faker.name().firstName(), faker.name().lastName()};

        var user = User.builder()
                .id(Long.parseLong("1"))
                .inn(Long.parseLong(inn))
                .password(passwordEncoder.encode(password))
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
                .user(userRepo.findByInn(Long.parseLong(inn)).get())
                .role(roleRepo.findByName(Constants.ROLE_ADMIN).get())
                .build();

        registrationJournalRepo.save(registrationJournal);
    }
    public static void saveAdminWithRoles(String inn, String password, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        saveRoles(roleRepo);
        saveAdminByInnAndPassword(inn, password, userRepo, roleRepo, registrationJournalRepo);
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

    private static String  getRandomGender(){
        if (rn.nextInt(2) == 0)
            return "Мужской";
        else
            return "Женский";
    }
}
