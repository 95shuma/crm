package com.project.crm.ui.steps;


import com.project.crm.frontend.forms.remediesForm.RemediesFormRegisterForm;
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

public class RemedyFormSteps extends Steps {
    @Autowired
    private Validator validator;

    private RemediesFormRegisterForm remediesFormRegisterForm;
    @Before
    public void start(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        remediesFormRegisterForm = new RemediesFormRegisterForm();
        setUp();
    }

    @After
    public void finish(){
        tearDown();
    }

    @Допустим("админ логинится и открывает список лекарств")
    public void админАвторизуетсяиОткрываетСписокЛекарств() {
        login("11111111111111","11111111111111");
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку форма и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("форма")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("вводит слово из букв и нажимает кнопку добавить")
    public void вводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааабббб");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список лекарственных форм")
    public void появляетсяСписокФорм() {
        Boolean flag=false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/forms")){
            flag=true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("вводит слово из букв и цифр и нажимает кнопку добавить")
    public void вводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("ничего не вводит и нажимает кнопку добавить")
    public void ничегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка Обязательное поле")
    public void выходитОшибкаОбязательноеПоле() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", getElementFromRemediesForm().getText());
    }

    @Тогда("выходят уведомление об ошибках")
    public void выходятУведомлениеОбОшибках() {
        Set<ConstraintViolation<RemediesFormRegisterForm>> violations = validator.validate(remediesFormRegisterForm);
        List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertEquals(2, violations.size());
        assertTrue(errorMessages.containsAll(Arrays.asList("Обязательное поле", "Название должно содержать только буквы : ")));
        assertFalse(violations.isEmpty());
    }

}
