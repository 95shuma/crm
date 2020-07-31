package com.project.crm.backend.services;

import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.repository.RecordJournalRepo;
import com.project.crm.backend.repository.RegistrationJournalRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.backend.repository.WorkScheduleRepo;
import com.project.crm.frontend.forms.WorkScheduleForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepo workScheduleRepo;
    private final RegistrationJournalRepo registrationJournalRepo;
    private final RecordJournalRepo recordJournalRepo;
    private final UserRepo userRepo;


    /*
    public void createWorkSchedule (WorkScheduleForm workScheduleForm){
        WorkSchedule workSchedule = WorkSchedule.builder()
                .date(workScheduleForm.getDate())
                .registrationJournal(registrationJournalRepo.findById(workScheduleForm.getRegJournalId()).orElseGet(RegistrationJournal::new))
                .dayStart(workScheduleForm.getDayStart())
                .dayEnd(workScheduleForm.getDayEnd())
                .lunchStart(workScheduleForm.getLunchStart())
                .lunchEnd(workScheduleForm.getLunchEnd())
                .build();
        workScheduleRepo.save(workSchedule);
    }

    public Page<WorkSchedule> getWorkSchedules(Long inn, Pageable pageable){
        return workScheduleRepo.findByRegistrationJournalUserInn(inn, pageable);
    }

    public List<LocalDateTime> getWorkSchedule(LocalDate date, Long inn){
        //------------------Поиск графика по ИНН доктора и дате--------------------------------------
        WorkSchedule workSchedule=workScheduleRepo.findByDateAndRegistrationJournalUserInn(date, inn);
        //------------------Построение времени приема до обеда---------------------------------------
        LocalDateTime localDateTime=workSchedule.getDayStart();
        long mod=Math.abs(Duration.between(workSchedule.getDayStart(),workSchedule.getLunchStart()).toMinutes())%20;
        List<LocalDateTime> workTimes = new ArrayList<LocalDateTime>();
        for (localDateTime=localDateTime.plusMinutes(mod);localDateTime.compareTo(workSchedule.getLunchStart())<0;localDateTime=localDateTime.plusMinutes(20)){
            workTimes.add(localDateTime);
        }
        //------------------Построение времени приема после обеда------------------------------------
        localDateTime=workSchedule.getLunchEnd();
        mod=Math.abs(Duration.between(workSchedule.getLunchEnd(),workSchedule.getDayEnd()).toMinutes())%20;
        for (localDateTime=localDateTime.plusMinutes(mod);localDateTime.compareTo(workSchedule.getDayEnd())<0;localDateTime=localDateTime.plusMinutes(20)){
            workTimes.add(localDateTime);
        }
        //------------------Построение занятых времни приема-----------------------------------------
        List<LocalDateTime> workBusyTimes = new ArrayList<LocalDateTime>();
        recordJournalRepo.findAllByDoctorIdAndDateTimeBetween(userRepo.findByInn(inn).orElseGet(User::new).getId(),workSchedule.getDayStart(),workSchedule.getDayEnd()).
                forEach(obj->{
                    workBusyTimes.add(obj.getDateTime());});
        //------------------Построение окончательного времени приема---------------------------------
        workTimes.removeAll(workBusyTimes);
        workTimes.removeIf(workTime->(workTime.isBefore(LocalDateTime.now())));
        return workTimes;
    }*/
}
