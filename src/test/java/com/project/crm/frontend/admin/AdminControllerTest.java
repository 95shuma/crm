package com.project.crm.frontend.admin;

import com.github.javafaker.Faker;
import com.project.crm.backend.model.User;
import com.project.crm.backend.model.catalog.RegistrationJournal;
import com.project.crm.backend.repository.*;
import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
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
import org.springframework.validation.FieldError;

import java.util.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)            //Что-то вроде типа теста. В данном случае Интеграционный. От данного параметра зависит как поведут себя анотации @Autowired, @MockBean, @Mock внутри класса
@SpringBootTest                         //Запускает тест -> Формирует ApplicationContext. Более подробно в #91 тикете.
@AutoConfigureMockMvc
public class AdminControllerTest extends RepoMethods {

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
    }

    @After      //После каждого теста чистим репозитории
    public void tearDown() {
        registrationJournalRepo.deleteAll();
        userRepo.deleteAll();
        hospitalRepo.deleteAll();
        roleRepo.deleteAll();
        positionRepo.deleteAll();
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
    }

    @Test
    public void createSeniorDoctor_expectInnExistsError() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/admin/senior-doctors")
                .with(csrf())
                .with(user(innAdmin).password(passwordAdmin).roles(Constants.ADMIN))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", innAdmin),
                        new BasicNameValuePair("password", passwordAdmin),
                        new BasicNameValuePair("documentNumber", "ID6666699"),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/admin/senior-doctors/senior-doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");
        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("inn", fieldErrors.get(0).getField());
        Assert.assertEquals(innAdmin, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот ИНН уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }

    @Test
    public void createSeniorDoctor_expectDocumentNumberExistsError() throws Exception {
        saveRepos();

        MvcResult mvcResult = mockMvc.perform(post("/admin/senior-doctors")
                .with(csrf())
                .with(user(innAdmin).password(passwordAdmin).roles(Constants.ADMIN))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("inn", "11111111111155"),
                        new BasicNameValuePair("password", passwordAdmin),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        )
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/admin/senior-doctors/senior-doctor"))
                .andExpect(flash().attributeExists("errors"))
                .andReturn();
        List<FieldError> fieldErrors = (List<FieldError>) mvcResult.getFlashMap().get("errors");
        Assert.assertEquals("userRegisterForm", fieldErrors.get(0).getObjectName());
        Assert.assertEquals("documentNumber", fieldErrors.get(0).getField());
        Assert.assertEquals(correctDocumentNumber, fieldErrors.get(0).getRejectedValue());
        Assert.assertEquals("Этот номер паспорта уже используется другим пользователем", fieldErrors.get(0).getDefaultMessage());
    }
}
