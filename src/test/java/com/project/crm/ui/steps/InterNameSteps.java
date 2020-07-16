package com.project.crm.ui.steps;


import com.project.crm.frontend.forms.remediesForm.InternationalNameRegisterForm;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InterNameSteps extends Steps {
    @Autowired
    private Validator validator;

    private InternationalNameRegisterForm internationalNameRegisterForm;
    @Before
    public void start(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        internationalNameRegisterForm = new InternationalNameRegisterForm();
        setUp();
    }

    @After
    public void finish(){
        tearDown();
    }

    @Допустим("пользователь авторизуется под админом и открывает список лекарств")
    public void пользовательАвторизуетсяПодАдминомиОткрываетСписокЛекарств() {
        login("11111111111111","11111111111111");
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку Международное название и кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("Международное название")).click();
        webDriver.findElement(By.name("intName")).click();
    }

    @Когда("пользователь вводит слово из букв и нажимает кнопку добавить")
    public void пользовательВводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("орпорпорпо");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список Международных названий лекарств")
    public void появляетсяСписокМеждународныхНазванийЛекарств() {
        Boolean flag=false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/internationalNames")){
            flag=true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("пользователь вводит слово из букв и цифр и нажимает кнопку добавить")
    public void пользовательВводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("пользователь  ничего не вводит и нажимает кнопку добавить")
    public void пользовательНичегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит сообщение об ошибке")
    public void выходитСообщениеОбОшибке() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", getElementFromInterName().getText());
    }

    @Тогда("выходят сообщения об ошибке")
    public void выходятСообщенияОбОшибке() {
        Set<ConstraintViolation<InternationalNameRegisterForm>> violations = validator.validate(internationalNameRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Обязательное поле", "Название должно содержать только буквы : ")));
        assertFalse(violations.isEmpty());
    }

}
