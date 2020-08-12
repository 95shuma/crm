package com.project.crm.backend.services;

import com.project.crm.backend.model.WorkSchedule;
import com.project.crm.backend.model.catalog.newScheduleModels.WeekDay;
import com.project.crm.backend.model.catalog.newScheduleModels.NewSchedule;
import com.project.crm.backend.repository.RegistrationJournalRepo;
import com.project.crm.backend.repository.WorkScheduleRepo;
import com.project.crm.frontend.forms.NewScheduleForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepo workScheduleRepo;
    private final RegistrationJournalRepo registrationJournalRepo;

    public void createWorkSchedule (NewSchedule newSchedule){
        newSchedule.getChosenRegUserList().stream().forEach(regUser -> {
            newSchedule.getWeekDayList().forEach(weekDay -> {
                workScheduleRepo.save(WorkSchedule.builder()
                        .registrationJournal(registrationJournalRepo.findById(Long.parseLong(regUser)).get())
                        .dayOfWeek(weekDay.getDayOfWeek().getValue())
                        .timeStart(weekDay.getFrom())
                        .timeEnd(weekDay.getTo())
                        .build()
                );
            });
        });
    }

    public NewSchedule fillNewSchedule(NewScheduleForm newScheduleForm){
        List<WeekDay> weekDayList = new ArrayList<>();
        if (newScheduleForm.getMondayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.MONDAY)
                    .from(newScheduleForm.getMondayFrom())
                    .to(newScheduleForm.getMondayTo())
                    .build());
        if (newScheduleForm.getTuesdayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.TUESDAY)
                    .from(newScheduleForm.getTuesdayFrom())
                    .to(newScheduleForm.getTuesdayTo())
                    .build());
        if (newScheduleForm.getWednesdayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.WEDNESDAY)
                    .from(newScheduleForm.getWednesdayFrom())
                    .to(newScheduleForm.getWednesdayTo())
                    .build());
        if (newScheduleForm.getThursdayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.THURSDAY)
                    .from(newScheduleForm.getThursdayFrom())
                    .to(newScheduleForm.getThursdayTo())
                    .build());
        if (newScheduleForm.getFridayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.FRIDAY)
                    .from(newScheduleForm.getFridayFrom())
                    .to(newScheduleForm.getFridayTo())
                    .build());
        if (newScheduleForm.getSaturdayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.SATURDAY)
                    .from(newScheduleForm.getSaturdayFrom())
                    .to(newScheduleForm.getSaturdayTo())
                    .build());
        if (newScheduleForm.getSundayFrom() != null)
            weekDayList.add(WeekDay.builder()
                    .dayOfWeek(DayOfWeek.SUNDAY)
                    .from(newScheduleForm.getSundayFrom())
                    .to(newScheduleForm.getSundayTo())
                    .build());
        return NewSchedule.builder()
                .chosenRegUserList(newScheduleForm.getChosenRegUser())
                .weekDayList(weekDayList)
                .build();
    }
    public List<WorkSchedule> getWorkScheduleListByRegUserId (Long regUserId){
        return workScheduleRepo.findAllByRegistrationJournalId(regUserId);
    }

    /*
    public void createWorkSchedule (NewSchedule workScheduleForm){
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
