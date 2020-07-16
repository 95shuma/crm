package com.project.crm.ui.steps;


import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
import com.project.crm.frontend.forms.remediesForm.RemedyTypeRegisterForm;
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

public class RemedyTypeSteps extends Steps {
    @Autowired
    private Validator validator;

    private RemedyTypeRegisterForm remedyTypeRegisterForm;
    @Before
    public void start(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        remedyTypeRegisterForm = new RemedyTypeRegisterForm();
        setUp();
    }

    @After
    public void finish(){
        tearDown();
    }

    @Допустим("он логинится и открывает список лекарств")
    public void АвторизуетсяиОткрываетСписокЛекарств() {
        login("11111111111111","11111111111111");
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку тип и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("тип")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("вводит значение из букв и нажимает кнопку добавить")
    public void вводитЗначениеИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааабббб");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список типов")
    public void появляетсяСписокТипов() {
        Boolean flag=false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/remedyTypes")){
            flag=true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("вводит значение из букв и цифр и нажимает кнопку добавить")
    public void вводитЗначениеИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("не заполняет и нажимает кнопку добавить")
    public void ничегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка об Обязательном поле")
    public void выходитОшибкаОбОбязательномПоле() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", getElementFromRemedyType().getText());
    }

    @Тогда("выходят уведомления об ошибках")
    public void выходятУведомленияОбОшибках() {
        Set<ConstraintViolation<RemedyTypeRegisterForm>> violations = validator.validate(remedyTypeRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Обязательное поле", "Название должно содержать только буквы : ")));
        assertFalse(violations.isEmpty());
    }

}
