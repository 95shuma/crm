package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.project.crm.backend.model.Patient;
import com.project.crm.backend.repository.AdministratorRepo;
import com.project.crm.backend.repository.DoctorRepo;
import com.project.crm.backend.repository.PatientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class PreloadDatabaseWithData {
    private static final Random rn = new Random();
    private static final Faker faker = new Faker();
    private static final FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService()
    );

    @Bean
    CommandLineRunner fillDatabase(AdministratorRepo administratorRepo, DoctorRepo doctorRepo, PatientRepo patientRepo){
        return (args) -> {
            administratorRepo.deleteAll();
            doctorRepo.deleteAll();
            patientRepo.deleteAll();

            //System.out.println(faker.numerify("Hello"));

            /*Patient.builder()
                    .inn(faker.)
                    .build()*/
        };
    }
}
