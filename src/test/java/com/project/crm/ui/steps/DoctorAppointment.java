package com.project.crm.ui.steps;

import com.project.crm.frontend.forms.RecordJournalRegisterForm;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DoctorAppointment extends Steps{

    @Autowired
    private Validator validator;

    @Допустим("врач входит на сайт и попадает к себе на страницу")
    public void врачВходитНаСайт() {
        webDriver.get("http://localhost:7777/login");
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("33333333333333");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("33333333333333");
        password.submit();
    }

    @Тогда("врач принимает пациента и создает examinationResult не заполняя поля")
    public void врачПринимаетПациентаИСоздаетExaminationResultНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("55555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("examinationResult")).click();
        webDriver.findElement(By.className("btn-primary")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходит ошибка Обязаельное поле.")
    public void выходитОшибкаОбязаельноеПоле(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("alert-warning")).getText());
        webDriver.close();
    }

    @Тогда("врач принимает пациента и создает diagnoseResult не заполняя поля")
    public void врачПринимаетПациентаИСоздаетDiagnoseResultНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("55555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("diagnoseResult")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходит ошибка Выберите один из вариантов")
    public void выходитОшибкаВыберитеОдинИзВариантов(){
        Assertions.assertEquals("Выберите один из вариантов", webDriver.findElement(By.className("alert-warning")).getText());
        webDriver.close();
    }

    @Тогда("врач принимает пациента и создает sickList не заполняя поля")
    public void врачПринимаетПациентаИСоздаетSickListНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("55555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("sickList")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходят ошибки Требуется ввести 7 цифр и др")
    public void выходятОшибки(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("startDate")).getText());
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("endDate")).getText());
        Assertions.assertEquals("Требуется ввести 7 цифр", webDriver.findElement(By.className("sickListNumber")).getText());
        webDriver.close();
    }
    @Тогда("врач принимает пациента и создает sickList заполнив только номер SF")
    public void врачПринимаетПациентаИСоздаетSickListЗаполнивТолькоНомерSF(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("55555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("sickList")).click();
        webDriver.findElement(By.name("number")).sendKeys("wwwwwww");
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходят ошибки № состоит только из цифр : и др")
    public void выходятОшибкиСостоитТолькоИзЦифр(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("startDate")).getText());
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("endDate")).getText());
        Assertions.assertEquals("№ состоит только из цифр : wwwwwww", webDriver.findElement(By.className("sickListNumber")).getText());
        webDriver.close();
    }

}
