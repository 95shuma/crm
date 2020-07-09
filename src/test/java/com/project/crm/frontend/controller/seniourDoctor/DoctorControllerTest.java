package com.project.crm.frontend.controller.seniourDoctor;

import com.project.crm.backend.repository.PlaceRepo;
import com.project.crm.backend.repository.RegistrationJournalRepo;
import com.project.crm.backend.repository.RoleRepo;
import com.project.crm.backend.repository.UserRepo;
import com.project.crm.backend.services.RegistrationJournalService;
import com.project.crm.backend.services.UserService;
import com.project.crm.backend.util.Constants;
import com.project.crm.frontend.forms.UserRegisterForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Calendar;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    UserService userService;
//    @MockBean
//    PropertiesService propertiesService;
//
//    ObjectMapper mapper = new ObjectMapper();
    @Mock
    PlaceRepo placeRepo;
    @Mock
    UserRepo userRepo;
    @Mock
    RoleRepo roleRepo;
    @Mock
    private RegistrationJournalService registrationJournalService;
    @Mock
    RegistrationJournalRepo registrationJournalRepo;

    private UserRegisterForm userRegisterForm;
    private Calendar calendar;
    private java.util.Date today;
    private String correctInn;
    private String correctPassword;
    private String correctDocumentNumber;
    private String correctName;
    private String correctSurname;
    private String correctMiddleName;
    private String correctGender;
    private String testString;

    private Model model;

    @Before
    public void setUp(){
        userRegisterForm = new UserRegisterForm();
        model = new ConcurrentModel();
        correctInn = "22222222222222";
        correctPassword = "22222222222222";
        correctDocumentNumber = "ID1234567";
        correctName = "Тест";
        correctSurname = "Тестов";
        correctMiddleName = "Тестович";
        correctGender = "мужской";
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        testString = "тест";
    }


    @Test
    public void getDoctors_checkMethod_expect() throws Exception {
        //Principal principal = () -> correctInn;

        this.mockMvc.perform(get("/senior-doctor/doctors/doctor")
                    .with(user(correctInn).password(correctPassword).roles(Constants.SENIOR_DOCTOR))
                ).andExpect(status().isOk())
                .andExpect(view().name("seniorDoctor/doctorController/doctorRegister"))
        ;

        //verify(userService, times(1)).checkUserPresence(model, principal);
        //verifyNoMoreInteractions(todoServiceMock);





        /*List<Role> roleList = new ArrayList<>();
        for (int i = 1; i<=5; i++){
            roleList.add(Role.builder()
                    .id(Long.parseLong(Integer.toString(i)))
                    .name(testString)
                    .build()
            );
        }
        when(roleRepo.findAll()).thenReturn(roleList);
        when(registrationJournalRepo.existsByUserInnAndRoleId(Long.parseLong(correctInn), Long.parseLong("3"))).thenReturn(true);       //когда цикл доходит до нужной роли return true. Тем самым понятно что if срабатывает на той роли, на которой нужно
        when(userRepo.findByInn(Long.parseLong(correctInn))).thenReturn(Optional.of(User.builder()
                .id((long) 1)
                .inn(Long.parseLong(correctInn))
                .password(correctPassword)
                .documentNumber(correctDocumentNumber)
                .name(correctName)
                .surname(correctSurname)
                .middleName(correctMiddleName)
                .fullName(correctSurname+" "+correctName+" "+correctMiddleName)
                .gender(correctGender)
                .place(Place.builder()
                        .id((long) 1)
                        .name(testString)
                        .codePlace((long) 11111111)
                        .groupCode((long)1)
                        .build())
                .birthDate(today)
                .build())
        );*/
    }


}