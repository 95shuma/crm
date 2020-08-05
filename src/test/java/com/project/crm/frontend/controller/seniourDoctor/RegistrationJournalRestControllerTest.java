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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationJournalRestControllerTest extends RepoMethods {
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
    public void getRegUsersByPosition_checkSuccessMethod_expectResponseWithChosenPositionAndSameHospitalAsAuthorizedUser() throws Exception {
        saveRepos();
        RegistrationJournal registrationJournalUser = registrationJournalRepo.findFirstByUserInnAndRoleId(Long.parseLong(innSeniorDoctor), roleRepo.findByName(Constants.ROLE_SENIOR_DOCTOR).get().getId());
        Position positionTest = positionRepo.findAll().get(0);
        MvcResult mvcResult = this.mockMvc.perform(get("/users/positions/"+positionTest.getId().toString())
                .with(user(registrationJournalUser.getUser().getInn().toString()).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))                  //Эмитируем авторизованный запрос - Иначе будет redirect 302
        ).andExpect(status().is(200))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        RegistrationJournalDTO[] response = objectMapper.readValue(contentAsString, RegistrationJournalDTO[].class);
        //проверим что RegistrationJournalDTO[] все пренадлежат одному hospital, идентичному авторизованному пользовалелю и positionId у них идентичный переданному
        for (RegistrationJournalDTO registrationJournalDTO: response){
            Assert.assertEquals(positionTest.getId() ,registrationJournalDTO.getPosition().getId());
            Assert.assertEquals(registrationJournalUser.getHospital().getId() ,registrationJournalDTO.getHospital().getId());
        }
    }

}
