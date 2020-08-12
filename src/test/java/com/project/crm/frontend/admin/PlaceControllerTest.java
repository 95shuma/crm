package com.project.crm.frontend.admin;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.Place;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
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
import org.springframework.validation.FieldError;

import java.util.*;

import static com.project.crm.backend.util.RepoMethods.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)            //Что-то вроде типа теста. В данном случае Интеграционный. От данного параметра зависит как поведут себя анотации @Autowired, @MockBean, @Mock внутри класса
@SpringBootTest                         //Запускает тест -> Формирует ApplicationContext. Более подробно в #91 тикете.
@AutoConfigureMockMvc
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    //Если указать @Autowired то тест запустится напрямую с БД, т.е. при тестовом repo.save произойдет реальное сохранение в БД
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

    private static Faker faker;
    private static Random rn;
    private int size;
    private Calendar calendar;
    private java.util.Date today;
    private String innAdmin;
    private String passwordAdmin;
    //Correct
    private String correctInn;
    private String correctPassword;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;
    private String testString;
    private Long correctCodePlace;
    private Long correctGroupCode;
    @Before
    public void setUp() {
        rn = new Random();
        faker = new Faker(new Locale("ru"));
        size = 5;
        innAdmin = "11111111111111";
        passwordAdmin = "11111111111111";
        //Correct
        correctInn = "12345678912345";
        correctPassword = "123456789";
        correctDocumentNumber = "ID6666666";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        testString = "тест";
        correctCodePlace = 14444444444466L;
        correctGroupCode = 5L;
    }

    @After      //После каждого теста чистим репозитории
    public void tearDown() {
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
        positionRepo.deleteAll();
        placeRepo.deleteAll();
    }

    public void saveRepos() {
        saveRolesConstant(roleRepo);
        savePositionsConstant(positionRepo);
        saveHospitalsWith(size, hospitalRepo, placeRepo);

        User user = User.builder()
                .inn(Long.parseLong(innAdmin))
                .password(passwordAdmin)
                .documentNumber(correctDocumentNumber)
                .name(correctName)
                .surname(correctSurname)
                .middleName(correctMiddleName)
                .fullName(correctSurname + " " + correctName + " " + correctMiddleName)
                .gender(correctGender)
                .birthDate(today)
                .place(placeRepo.findAll().get(0))
                .build();
        userRepo.save(user);

        RegistrationJournal registrationJournal = RegistrationJournal.builder()
                .role(roleRepo.findByName(Constants.ROLE_ADMIN).get())
                .user(user)
                .hospital(hospitalRepo.findAll().get(0))
                .build();

        registrationJournalRepo.save(registrationJournal);

        Place place = Place.builder()
                .codePlace(correctCodePlace)
                .groupCode(correctGroupCode)
                .name(correctName)
                .build();

        placeRepo.save(place);
    }

    @Test       //Проверем что при Post запросе c неправильными данными будут ошибки
    public void createPlace_expectValidationErrors() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/admin/places")
                .with(csrf())
                .with(user(innAdmin).password(passwordAdmin).roles(Constants.ADMIN))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                     //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("groupCode", correctGroupCode.toString()),
                        new BasicNameValuePair("codePlace", correctCodePlace.toString()),
                        new BasicNameValuePair("name", correctName))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/admin/places/place"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");

        Assert.assertEquals("placeRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("codePlace", fieldErrors.get(0).getField());
        Assert.assertEquals(correctCodePlace.toString(), fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот код места уже используется", fieldErrors.get(0).getDefaultMessage());
    }
}
