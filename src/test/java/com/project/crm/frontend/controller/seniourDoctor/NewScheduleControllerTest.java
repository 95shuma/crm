package com.project.crm.frontend.controller.seniourDoctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.project.crm.backend.dto.RegistrationJournalDTO;
import com.project.crm.backend.model.catalog.Position;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.*;
import com.project.crm.backend.util.Constants;
import com.project.crm.backend.util.RepoMethods;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewScheduleControllerTest extends RepoMethods {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RegistrationJournalService registrationJournalService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PositionService positionService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    RegistrationJournalRepo registrationJournalRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    HospitalRepo hospitalRepo;
    @Autowired
    PositionRepo positionRepo;
    @Autowired
    ObjectMapper objectMapper;

    private static Faker faker;
    private static Random rn;
    private int size;
    private String innSeniorDoctor;
    private String passwordSeniorDoctor;

    @Before
    public void setUp() {
        rn = new Random();
        faker = new Faker(new Locale("ru"));
        size = 5;
        innSeniorDoctor = Constants.SENIOR_DOCTOR_INN;
        passwordSeniorDoctor = Constants.SENIOR_DOCTOR_PASSWORD;
    }

    @After      //После каждого теста чистим репозитории
    public void tearDown() {
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
        positionRepo.deleteAll();
    }

    //Этот метод добавляет необходимые для данного класса репозитории. В трех тестах используется, поэтому вынес в отдельный метод
    public void saveRepos() {
        saveRolesConstant(roleRepo);
        savePositionsConstant(positionRepo);
        saveHospitalsWith(size, hospitalRepo, placeRepo);
        saveDoctorsConstant(userRepo, hospitalRepo, roleRepo, positionRepo, registrationJournalRepo);
        saveRandomUsersBasedOnAnotherUserAtTheSameHospital(25, registrationJournalRepo.findFirstByUserInnAndRoleId(Long.parseLong(Constants.SENIOR_DOCTOR_INN), roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId()),
                roleRepo, positionRepo, hospitalRepo, userRepo, placeRepo, registrationJournalRepo);
    }

    @Test   //Проверят успешный Get запрос.
    public void createSchedule_checkSuccessMethod_expect() throws Exception {
        saveRepos();
        RegistrationJournal registrationJournalUser = registrationJournalRepo.findFirstByUserInnAndRoleId(Long.parseLong(innSeniorDoctor), roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId());
        Position positionTest = positionRepo.findAll().get(0);

        MvcResult mvcResult = mockMvc.perform(post("/senior-doctor/schedules/new-schedule")
                .with(csrf())
                .with(user(registrationJournalUser.getUser().getInn().toString()).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("chosenRegUser", registrationJournalRepo.findByHospitalIdAndPositionId(registrationJournalUser.getHospital().getId(), roleRepo.findAll().get(0).getId()).get(0).getId().toString()),
                        new BasicNameValuePair("chosenRegUser", registrationJournalRepo.findByHospitalIdAndPositionId(registrationJournalUser.getHospital().getId(), roleRepo.findAll().get(0).getId()).get(1).getId().toString()),
                        new BasicNameValuePair("monday_from", LocalTime.of(9, 0).toString()),
                        new BasicNameValuePair("monday_to", LocalTime.of(18, 0).toString())
                ))))
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:"))
                .andReturn();

    }

}
