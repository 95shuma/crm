package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.*;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.Diagnose;
import com.project.crm.backend.model.catalog.medicalHistoryCatalog.DiagnoseResult;
import com.project.crm.backend.model.catalog.remediesCatalog.*;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.repository.medicalHistoryCatalogRepo.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RepoMethods {
    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static Date date = new Date();



    // --< ========================================= SAVE методы =========================================
    // Струтура наименовий методов: save + Repo + Type/With
    //  Type:
    //      Constant -> сохранят те данные, которые передаются в параметры
    //      Random -> Генерирует полностью рандомный список
    //      Если не указано -> Random по умолчанию
    //  With: -> Сохраняются несколько репозиториев.
    //          Например: saveHospitalsWithPlaces() -> Так как для сохранения  Hospitals нужны Places, сначала сделает savePlaces, потом saveHospitals. Чтобы не вызывать 2 метода подряд.

    public static void saveRemedyTypes(int qty, RemedyTypeRepo remedyTypeRepo) {
        List<RemedyType> typeList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            typeList.add(RemedyType.builder()
                    .name(faker.name().title())
                    .build()
            );
        }
        remedyTypeRepo.saveAll(typeList);
    }

    public static void saveRemedyForms(int qty, RemediesFormRepo remediesFormRepo) {
        List <RemediesForm> formList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            formList.add(RemediesForm.builder()
                    .name(faker.name().nameWithMiddle())
                    .build()
            );
        }
        remediesFormRepo.saveAll(formList);
    }
    public static void savePharmacologicalGroups(int qty, PharmacologicalGroupRepo pharmacologicalGroupRepo) {
        List <PharmacologicalGroup> groupList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            groupList.add(PharmacologicalGroup.builder()
                    .name(faker.pokemon().name())
                    .build()
            );
        }
        pharmacologicalGroupRepo.saveAll(groupList);
    }
    public static void saveMeasures(int qty, MeasureRepo measureRepo) {
        List <Measure> measureList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            measureList.add(Measure.builder()
                    .name(faker.name().prefix())
                    .build()
            );
        }
        measureRepo.saveAll(measureList);
    }
    public static void saveInternationalNames(int qty, InternationalNameRepo internationalNameRepo) {
        List <InternationalName> intNameList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            intNameList.add(InternationalName.builder()
                    .name(faker.ancient().god())
                    .build()
            );
        }
        internationalNameRepo.saveAll(intNameList);
    }
    public static void saveDosages(int qty, DosageRepo dosageRepo, MeasureRepo measureRepo) {
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
    public static void saveDosagesWithMeasureRepo(int qty, DosageRepo dosageRepo, MeasureRepo measureRepo) {
        saveMeasures(qty, measureRepo);
        saveDosages(qty, dosageRepo, measureRepo);
    }
    public static void savePlaces(int qty, PlaceRepo placeRepo) {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            placeList.add(Place.builder()
                    .name(faker.address().fullAddress())
                    .codePlace((long) faker.number().numberBetween(10, 30))
                    .groupCode((long) faker.number().numberBetween(1, 5))
                    .build()
            );
        }
        placeRepo.saveAll(placeList);
    }
    public static void saveRolesConstant(RoleRepo roleRepo){
        String[] roles = {Constants.ROLE_ADMIN, Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_PATIENT};
        for (int i=0; i<roles.length; i++){
            roleRepo.insertRoleWithId(Long.parseLong(Integer.toString(i+1)), roles[i]);
        }
    }
    public static void saveHospitals(int qty, HospitalRepo hospitalRepo, PlaceRepo placeRepo){
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
    public static void saveHospitalsWithPlaces(int qty, HospitalRepo hospitalRepo, PlaceRepo placeRepo){
        savePlaces(qty, placeRepo);
        saveHospitals(qty, hospitalRepo, placeRepo);
    }
    public static void savePositionsConstant(PositionRepo positionRepo){
        String[] positions = {"терапевт", "кардиолог", "лор", "детский врач", "офтальмолог"};
        for (int i=0; i<positions.length; i++){
            positionRepo.insertPositionWithId(Long.parseLong(Integer.toString(i+1)), positions[i]);
        }
    }
    public static void savePositionsRandom(int qty, PositionRepo positionRepo){
        List<Position> positionList = new ArrayList<>();
        for (int i=0; i<qty; i++){
            positionList.add(Position.builder()
                    .name(faker.job().position())
                    .build()
            );
        }
        positionRepo.saveAll(positionList);
    }
    public static void saveAdminConstant(String inn, String password, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
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
        saveRolesConstant(roleRepo);
        saveAdminConstant(inn, password, userRepo, roleRepo, registrationJournalRepo);
    }
    public static void saveUserRandom(int qty, String roleName, Boolean randomPosition, UserRepo userRepo, RoleRepo roleRepo, HospitalRepo hospitalRepo, PlaceRepo placeRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        for (int i=0;i<qty;i++){
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
            if (randomPosition)
                saveUserInRegistrationJournal(userRepo.findByInn(Long.parseLong(inn)).get(), roleRepo.findByName(roleName).get(), positionRepo.findAll().get(positionRepo.findAll().size()), hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())), registrationJournalRepo);
            else
                saveUserInRegistrationJournal(userRepo.findByInn(Long.parseLong(inn)).get(), roleRepo.findByName(roleName).get(), null, hospitalRepo.findAll().get(rn.nextInt(hospitalRepo.findAll().size())), registrationJournalRepo);
        }
    }
    public static void saveUserConstant(String inn, String password, String roleName, Place place, Hospital hospital, Position position, UserRepo userRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
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
    public static void saveDoctorsRandomForEachHospital(int doctorQty, int juniorDoctorQty,UserRepo userRepo, HospitalRepo hospitalRepo, PositionRepo positionRepo, RoleRepo roleRepo, RegistrationJournalRepo registrationJournalRepo){
        for (Hospital hospital : hospitalRepo.findAll()) {
            String inn = getUniqueINN(userRepo);
            saveUserConstant(inn, inn, Constants.ROLE_SENIOR_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            for (int i=0; i<doctorQty; i++) {
                inn = getUniqueINN(userRepo);
                saveUserConstant(inn, inn, Constants.ROLE_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            }
            for (int i=0; i<juniorDoctorQty; i++) {
                inn = getUniqueINN(userRepo);
                saveUserConstant(inn, inn, Constants.ROLE_JUNIOR_DOCTOR, hospital.getPlace(), hospital, positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
            }
        }
    }
    public static void saveDoctorsConstant(UserRepo userRepo, HospitalRepo hospitalRepo, RoleRepo roleRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        saveUserConstant(Constants.SENIOR_DOCTOR_INN, Constants.SENIOR_DOCTOR_PASSWORD, Constants.ROLE_SENIOR_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
        saveUserConstant(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD, Constants.ROLE_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
        saveUserConstant(Constants.JUNIOR_DOCTOR_INN, Constants.JUNIOR_DOCTOR_PASSWORD, Constants.ROLE_JUNIOR_DOCTOR, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())), userRepo, roleRepo, registrationJournalRepo);
    }
    public static void savePatientsConstant(UserRepo userRepo, HospitalRepo hospitalRepo, RoleRepo roleRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        saveUserConstant(Constants.PATIENT_INN, Constants.PATIENT_PASSWORD, Constants.ROLE_PATIENT, hospitalRepo.findAll().get(0).getPlace(), hospitalRepo.findAll().get(0), null, userRepo, roleRepo, registrationJournalRepo);
    }
    public static void savePatientsRandom(int qty, UserRepo userRepo, HospitalRepo hospitalRepo, RoleRepo roleRepo, PlaceRepo placeRepo, PositionRepo positionRepo, RegistrationJournalRepo registrationJournalRepo){
        saveUserRandom(qty, Constants.ROLE_PATIENT, false, userRepo, roleRepo, hospitalRepo, placeRepo, positionRepo, registrationJournalRepo);
    }
    public static void saveDiseases(int qty, DiseaseRepo diseaseRepo){
        List <Disease> diseaseList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            diseaseList.add(Disease.builder()
                    .name(faker.gameOfThrones().character())
                    .build()
            );
        }
        diseaseRepo.saveAll(diseaseList);
    }
    public static void saveRemedies(int qty, RemedyRepo remedyRepo, RemedyTypeRepo remedyTypeRepo, RemediesFormRepo remediesFormRepo, PharmacologicalGroupRepo pharmacologicalGroupRepo, InternationalNameRepo internationalNameRepo, DosageRepo dosageRepo){
        List <Remedy> remedyList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
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
    }
    public static void saveRemediesWith(int qty, RemedyRepo remedyRepo, RemedyTypeRepo remedyTypeRepo, RemediesFormRepo remediesFormRepo, PharmacologicalGroupRepo pharmacologicalGroupRepo, InternationalNameRepo internationalNameRepo, DosageRepo dosageRepo, MeasureRepo measureRepo){
        saveRemedyTypes(qty, remedyTypeRepo);
        saveRemedyForms(qty, remediesFormRepo);
        savePharmacologicalGroups(qty, pharmacologicalGroupRepo);
        saveInternationalNames(qty, internationalNameRepo);
        saveDosagesWithMeasureRepo(qty, dosageRepo, measureRepo);
        saveRemedies(qty, remedyRepo, remedyTypeRepo, remediesFormRepo, pharmacologicalGroupRepo, internationalNameRepo, dosageRepo);
    }
    public static void saveExaminations(int qty, ExaminationRepo examinationRepo){
        List <Examination> examinationList = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            examinationList.add(Examination.builder()
                    .name(faker.esports().player())
                    .build()
            );
        }
        examinationRepo.saveAll(examinationList);
    }
    public static void saveMedicalHistory(int qty, MedicalHistoryRepo medicalHistoryRepo){
        List <MedicalHistory> medicalHistories = new ArrayList<>();

        for (int i = 0; i < qty; i++){
            if(i % 2 == 0)
                medicalHistories.add(MedicalHistory.builder()
                        .date(date)
                        .typeOfVisit(true)
                        .complaint(faker.music().instrument())
                        .recommendation(faker.lorem().characters())
                        .build());
            else
                medicalHistories.add(MedicalHistory.builder()
                        .date(date)
                        .typeOfVisit(false)
                        .complaint(faker.music().instrument())
                        .recommendation(faker.lorem().characters())
                        .build());
        }
        medicalHistoryRepo.saveAll(medicalHistories);
    }
    public static void saveRecordJournal(int qty, RecordJournalRepo recordJournalRepo, UserRepo userRepo, MedicalHistoryRepo medicalHistoryRepo, HospitalRepo hospitalRepo){
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
    }
    public static void saveDiagnoses(int qty, DiagnoseRepo diagnoseRepo, PositionRepo positionRepo){
        List <Diagnose> diagnoses = new ArrayList<>();
        for (int i = 0; i < qty; i++){
            diagnoses.add(Diagnose.builder()
                    .isdCode(faker.superhero().name())
                    .name(faker.book().title())
                    .position(positionRepo.findAll().get(rn.nextInt(positionRepo.findAll().size())))
                    .build());

        }
        diagnoseRepo.saveAll(diagnoses);
    }
    public static void saveDiagnosesWith(int qty, DiagnoseRepo diagnoseRepo, PositionRepo positionRepo){
        savePositionsRandom(qty, positionRepo);
        saveDiagnoses(qty, diagnoseRepo, positionRepo);
    }
    public static void saveDiagnoseResults(int qty, DiagnoseResultRepo diagnoseResultRepo, DiagnoseRepo diagnoseRepo, MedicalHistoryRepo medicalHistoryRepo){
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
    }
    public static void saveDiagnoseResultsWith(int qty, DiagnoseResultRepo diagnoseResultRepo, DiagnoseRepo diagnoseRepo,MedicalHistoryRepo medicalHistoryRepo, PositionRepo positionRepo){
        saveDiagnosesWith(qty, diagnoseRepo, positionRepo);
        saveMedicalHistory(qty, medicalHistoryRepo);
        saveDiagnoseResults(qty, diagnoseResultRepo, diagnoseRepo, medicalHistoryRepo);
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
