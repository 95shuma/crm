package com.project.crm.backend.util;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.catalog.Hospital;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.repository.HospitalRepo;
import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.backend.repository.PositionRepo;
import com.project.crm.backend.repository.RoleRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CreateMethods {
    private static final Random rn = new Random();
    private static final Faker faker = new Faker(new Locale("ru"));

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

    public static void savePositions(PositionRepo positionRepo){
        String[] positions = {"терапевт", "кардиолог", "лор", "детский врач", "офтальмолог"};
        for (int i=0; i<positions.length; i++){
            positionRepo.insertPositionWithId(Long.parseLong(Integer.toString(i+1)), positions[i]);
        }
    }
    //Для создания Hospitals в БД должны быть Places поэтому сохраняем вместе
    public static void saveHospitalsWithPlaces(HospitalRepo hospitalRepo, PlaceRepo placeRepo,  int size){
        savePlaces(placeRepo, size);
        List<Hospital> hospitalList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            hospitalList.add(Hospital.builder()
                    .name(faker.company().name())
                    .place(placeRepo.findAll().get(rn.nextInt(placeRepo.findAll().size())))
                    .address(faker.address().streetAddress())
                    .build()
            );
        }
        hospitalRepo.saveAll(hospitalList);
    }



}
