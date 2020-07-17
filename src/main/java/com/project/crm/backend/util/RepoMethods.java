package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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
                .inn(Long.parseLong(inn))
                .password(passwordEncoder.encode(password))
                .documentNumber("ID".concat(faker.number().digits(7)))
                .fullName(adminName[1] + adminName[0] + adminName[2] + adminName[0] + adminName[3])
                .surname(adminName[1])
                .name(adminName[2])
                .middleName(adminName[3])
                .birthDate(faker.date().birthday())
                .gender(getRandomGender())
                .place(null)
                .enabled(true)
                .build();
        userRepo.save(user);
        saveUserInRegistrationJournal(userRepo.findByInn(Long.parseLong(inn)).get(), roleRepo.findByName(Constants.ROLE_ADMIN).get(), null, null, registrationJournalRepo);
    }
    public static void saveAdminWithRoles(String inn, String password, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        saveRoles(roleRepo);
        saveAdminByInnAndPassword(inn, password, userRepo, roleRepo, registrationJournalRepo);
    }
    public static void saveUserRandomByRole(String roleName, UserRepo userRepo, RoleRepo roleRepo, HospitalRepo hospitalRepo, PlaceRepo placeRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        String inn = getUniqueINN(userRepo);
        User user = User.builder()
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
                .build();
        userRepo.save(user);
        saveUserInRegistrationJournal(userRepo.findByInn(Long.parseLong(inn)).get(), roleRepo.findByName(roleName).get(), positionRepo.findAll().get(positionRepo.findAll().size()), hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())), registrationJournalRepo);
    }
    public static void saveUserConstantByRole(String inn, String password, String roleName, Place place, Hospital hospital, Position position, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        User user = User.builder()
                .inn(Long.parseLong(inn))
                .password(passwordEncoder.encode(password))
                .documentNumber("ID".concat(faker.number().digits(7)))
                .fullName(faker.name().fullName())
                .surname(faker.name().lastName())
                .name(faker.name().firstName())
                .middleName(faker.name().lastName())
                .birthDate(faker.date().birthday())
                .gender(getRandomGender())
                .place(place)
                .enabled(true)
                .build();
        userRepo.save(user);
        saveUserInRegistrationJournal(user, roleRepo.findByName(roleName).get(), position, hospital, registrationJournalRepo);
    }
    public static void saveDoctorsWithRandomInnForEachHospital(int doctorQty, int juniorDoctorQty,UserRepo userRepo, HospitalRepo hospitalRepo, PositionRepo positionRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        for (Hospital hospital : hospitalRepo.findAll()) {
            String inn = getUniqueINN(userRepo);
            saveUserConstantByRole(inn, inn, Constants.ROLE_SENIOR_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            for (int i=0; i<doctorQty; i++) {
                inn = getUniqueINN(userRepo);
                saveUserConstantByRole(inn, inn, Constants.ROLE_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            }
            for (int i=0; i<juniorDoctorQty; i++) {
                inn = getUniqueINN(userRepo);
                saveUserConstantByRole(inn, inn, Constants.ROLE_JUNIOR_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            }
        }
    }
    public static void saveDoctorsConstant(UserRepo userRepo, HospitalRepo hospitalRepo, RoleRepo roleRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        saveUserConstantByRole(Constants.SENIOR_DOCTOR_INN, Constants.SENIOR_DOCTOR_PASSWORD, Constants.ROLE_SENIOR_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
        saveUserConstantByRole(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD, Constants.ROLE_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
        saveUserConstantByRole(Constants.JUNIOR_DOCTOR_INN, Constants.JUNIOR_DOCTOR_PASSWORD, Constants.ROLE_JUNIOR_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
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
    private static String getUniqueINN(UserRepo userRepo){
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
        if (makeNew.get())
            return newInn;
        else
            return getUniqueINN(userRepo);
    }
    private static void saveUserInRegistrationJournal(User user, Role role, Position position, Hospital hospital, RegistrationJournalRepo registrationJournalRepo){
        var registrationJournal = RegistrationJournal.builder()
                .user(user)
                .role(role)
                .position(position)
                .hospital(hospital)
                .build();
        registrationJournalRepo.save(registrationJournal);
    }
}
