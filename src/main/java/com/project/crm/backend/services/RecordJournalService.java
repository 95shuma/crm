package com.project.crm.backend.services;

import com.project.crm.backend.dto.RecordJournalDTO;
import com.project.crm.backend.dto.UserDTO;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.MedicalHistory;
import com.project.crm.backend.model.catalog.RecordJournal;
import com.project.crm.backend.repository.HospitalRepo;
import com.project.crm.backend.repository.MedicalHistoryRepo;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordJournalService {

    private final RecordJournalRepo recordJournalRepo;
    private final UserRepo userRepo;
    private final MedicalHistoryRepo medicalHistoryRepo;
    private final HospitalRepo hospitalRepo;
    private final UserService userService;


    public List<RecordJournal> getAllByPatient(Principal principal){
        return recordJournalRepo.findByPatientInn(Long.parseLong(principal.getName()));
    }

    public List<RecordJournal> getAll(){return recordJournalRepo.findAll();}

    public RecordJournalDTO getById(String recordJournalId){return RecordJournalDTO.from(recordJournalRepo.findById(Long.parseLong(recordJournalId)).get());}

    public Page<RecordJournalDTO> getPatientsByDoctor(Long id, Pageable pageable) {

        return recordJournalRepo.findAllByDoctorIdOrderByDateTime(id, pageable).map(RecordJournalDTO::from);
    }

    public Page<RecordJournalDTO> getPatientsByDoctorAndToday(Long id, Pageable pageable) {

        LocalDateTime startDate = LocalDate.now().atTime(6,0,0);
        LocalDateTime endDate = LocalDate.now().atTime(23,59,0);

        return recordJournalRepo.findAllByDoctorIdAndDateTimeBetween(id, startDate, endDate, pageable)
                .map(RecordJournalDTO::from);
    }

    public List<RecordJournal> getByDoctor(Long doctor){
        return recordJournalRepo.findByDoctorId(doctor);
    }

    public Page<RecordJournal> getByPatient(Long inn, Pageable pageable){
        return recordJournalRepo.findByPatientInn(inn, pageable);
    }
    public Page<RecordJournal> getByPatientId(Long id, Pageable pageable){
        return recordJournalRepo.findByPatientId(id, pageable);
    }

    public Page<UserDTO> getAcceptedPatients(User user, Pageable pageable){
        return recordJournalRepo.findAllAcceptedPatients(user, pageable).map(UserDTO::from);
    }

    public RecordJournalDTO createRecordJournal(RecordJournalRegisterForm recordJournalRegisterForm, Principal principal){

            RecordJournal recordJournal;

            if(recordJournalRegisterForm.getMedicalHistoryId() == 0){
                Date date = new Date();
                MedicalHistory medicalHistory = MedicalHistory.builder()
                        .date(date)
                        .typeOfVisit(true)
                        .complaint(recordJournalRegisterForm.getReason())
                        .recommendation("")
                        .build();

                medicalHistoryRepo.save(medicalHistory);
                if(recordJournalRegisterForm.getRegistrarId() != null){

                    recordJournal = RecordJournal.builder()
                            .doctor(userRepo.findByInn(recordJournalRegisterForm.getDoctorId()).get())
                            .hospital(hospitalRepo.findById(recordJournalRegisterForm.getHospitalId()).get())
                            .patient(userRepo.findById(userService.getByInn(Long.parseLong(principal.getName())).getId()).get())
                            .registrar(userRepo.findById(userService.getByInn(recordJournalRegisterForm.getRegistrarId()).getId()).get())
                            .medicalHistory(medicalHistory)
                            .reason(recordJournalRegisterForm.getReason())
                            .dateTime(recordJournalRegisterForm.getDateTime())
                            .dateTimeNow(LocalDateTime.now())
                            .build();
                    return RecordJournalDTO.from(recordJournalRepo.save(recordJournal));
                }
                else{
                    recordJournal = RecordJournal.builder()
                            .doctor(userRepo.findByInn(recordJournalRegisterForm.getDoctorId()).get())
                            .hospital(hospitalRepo.findById(recordJournalRegisterForm.getHospitalId()).get())
                            .patient(userRepo.findById(userService.getByInn(Long.parseLong(principal.getName())).getId()).get())
                            .medicalHistory(medicalHistory)
                            .reason(recordJournalRegisterForm.getReason())
                            .dateTime(recordJournalRegisterForm.getDateTime())
                            .dateTimeNow(LocalDateTime.now())
                            .build();
                    return RecordJournalDTO.from(recordJournalRepo.save(recordJournal));
                }
            }
            else{
                if(recordJournalRegisterForm.getRegistrarId() != null){

                    recordJournal = RecordJournal.builder()
                            .doctor(userRepo.findByInn(recordJournalRegisterForm.getDoctorId()).get())
                            .hospital(hospitalRepo.findById(recordJournalRegisterForm.getHospitalId()).get())
                            .patient(userRepo.findById(userService.getByInn(Long.parseLong(principal.getName())).getId()).get())
                            .registrar(userRepo.findById(userService.getByInn(recordJournalRegisterForm.getRegistrarId()).getId()).get())
                            .medicalHistory(medicalHistoryRepo.findById(recordJournalRegisterForm.getMedicalHistoryId()).get())
                            .reason(recordJournalRegisterForm.getReason())
                            .dateTime(recordJournalRegisterForm.getDateTime())
                            .dateTimeNow(LocalDateTime.now())
                            .build();
                    return RecordJournalDTO.from(recordJournalRepo.save(recordJournal));
                }
                else{
                    recordJournal = RecordJournal.builder()
                            .doctor(userRepo.findByInn(recordJournalRegisterForm.getDoctorId()).get())
                            .hospital(hospitalRepo.findById(recordJournalRegisterForm.getHospitalId()).get())
                            .patient(userRepo.findById(userService.getByInn(Long.parseLong(principal.getName())).getId()).get())
                            .medicalHistory(medicalHistoryRepo.findById(recordJournalRegisterForm.getMedicalHistoryId()).get())
                            .reason(recordJournalRegisterForm.getReason())
                            .dateTime(recordJournalRegisterForm.getDateTime())
                            .dateTimeNow(LocalDateTime.now())
                            .build();
                    return RecordJournalDTO.from(recordJournalRepo.save(recordJournal));
                }
            }

    }
}
