package com.project.crm.frontend.controller.seniourDoctor;

import com.project.crm.backend.services.PlaceService;
import com.project.crm.backend.services.PositionService;
import com.project.crm.backend.services.RoleService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Calendar;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)            //Что-то вроде типа теста. В данном случае Интеграционный. От данного параметра зависит как поведут себя анотации @Autowired, @MockBean, @Mock внутри класса
@SpringBootTest                         //Запускает тест -> Формирует ApplicationContext. Более подробно в #91 тикете.
@AutoConfigureMockMvc                   //Конфигурирует mockMVC
public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean                           //Если указать @Autowired то тест запустится напрямую с БД, т.е. при тестовом repo.save произойдет реальное сохранение в БД
    private UserService userService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private PlaceService placeService;
    @MockBean
    private PositionService positionService;

    private UserRegisterForm userRegisterForm;
    private Calendar calendar;
    private java.util.Date today;
    private String innSeniorDoctor;
    private String passwordSeniorDoctor;
    //Correct
    private String correctInn;
    private String correctPassword;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;
    private String testString;
    //Wrong

    private Model model;

    @Before
    public void setUp(){
        innSeniorDoctor = "22222222222222";
        passwordSeniorDoctor = "22222222222222";
        //Correct
        correctInn = "12345678912345";
        correctPassword = "123456789";
        correctDocumentNumber = "ID1234567";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        testString = "тест";
        //Wrong

    }
    @Test   //Проверят успешный Get запрос.
    public void getDoctors_checkMethod_expect() throws Exception {
        this.mockMvc.perform(get("/senior-doctor/doctors/doctor")
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))          //Эмитируем авторизованный запрос - Иначе будет redirect 302
        ).andExpect(status().isOk())                                                                                //Ожидается успешный ответ 200
        .andExpect(view().name("seniorDoctor/doctorController/doctorRegister"))                    //Ожидается return на view
        .andExpect(model().attribute("user", userService.getByInn(Long.parseLong(innSeniorDoctor))))          //Под кем авторизовались, должен отобразиться в model attribute - user
        //.andExpect(model().attribute("reg", new UserRegisterForm()))
        .andExpect(model().attribute("places", placeService.getAll()))                                        //Далее аналогично с User
        .andExpect(model().attribute("roles", roleService.getAll()))
        .andExpect(model().attribute("positions", positionService.getAll()))
        .andExpect(model().attribute(Constants.ROLE_SENIOR_DOCTOR, Constants.ROLE_SENIOR_DOCTOR))
        .andExpect(model().attribute(Constants.ROLE_DOCTOR, Constants.ROLE_DOCTOR))
        .andExpect(model().attribute(Constants.ROLE_JUNIOR_DOCTOR, Constants.ROLE_JUNIOR_DOCTOR));
    }
    @Test
    public void getDoctors_checkWrongMethodWithoutAuthorization_ExpectRedirect_Status302() throws Exception {
        mockMvc.perform(get("/senior-doctor/doctors/doctor")
        ).andExpect(status().is(302))
        .andExpect(redirectedUrl(Constants.LINK_HTTP + Constants.LINK_LOCALHOST + "/login"));
    }

    //AddDoctor
    @Test
    public void addDoctors_checkMethod_shouldRedirectToViewAfterSave() throws Exception {                            //Так как указан MockBean сохранение не происходит.
        mockMvc.perform(post("/senior-doctor/doctors/doctor")                                               //Эмитируем Post запрос на нужную страницу
                .with(csrf())                                                                                          //Добавляем token в параметы запроса. Иначе будет ошибка 403
                .with(user(innSeniorDoctor).password(passwordSeniorDoctor).roles(Constants.SENIOR_DOCTOR))             //Эмитируем авторизованный запрос - Иначе будет redirect 302
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)                                                    //Тип данных при запросе
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(                                   //Далее передается форма в параметры запроса
                        new BasicNameValuePair("inn", correctInn),
                        new BasicNameValuePair("password", correctPassword),
                        new BasicNameValuePair("documentNumber", correctDocumentNumber),
                        new BasicNameValuePair("surname", correctSurname),
                        new BasicNameValuePair("name", correctName),
                        new BasicNameValuePair("middleName", correctMiddleName),
                        new BasicNameValuePair("birthDate", "1995-10-28"),
                        new BasicNameValuePair("gender", correctGender),
                        new BasicNameValuePair("placeId", "1"),
                        new BasicNameValuePair("positionId", "1"),
                        new BasicNameValuePair("roleId", "1"),
                        new BasicNameValuePair("hospitalId", "1"))))
                )
        ).andExpect(status().is(302))                                                                           //Если все прошло успешно, то додет до redirect на нужную страницу
                .andExpect(view().name("redirect:/senior-doctor"));                                  //Соответственно проверяем страницу
    }
}