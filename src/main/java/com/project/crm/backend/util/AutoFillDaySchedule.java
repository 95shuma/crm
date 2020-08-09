package com.project.crm.backend.util;

import com.project.crm.backend.model.WorkSchedule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.*;

//@Configuration
public class AutoFillDaySchedule{
    /*
    private static final Random rn = new Random();
    private int cnt = 0;
    long period = 1000L *  60L *  60L *  24L * 7L * 2L;
    @Bean
    void autoFillDaySchedule(){
        TimerTask fillDayScheduleTask = new TimerTask() {
            public void run() {



            }
        };
        Timer timer = new Timer("DoctorsDaySchedule");
                                                //Каждые 2 недели
        timer.scheduleAtFixedRate(fillDayScheduleTask, Date.from(Instant.from(DayOfWeek.MONDAY)), period);
    }*/
}

