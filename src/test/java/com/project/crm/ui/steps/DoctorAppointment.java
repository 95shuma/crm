package com.project.crm.ui.steps;


import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.junit.Assert.*;

public class DoctorAppointment extends Steps{

    @Before("@doctorAppointment")
    public void start() {
        setUp();
    }

    @After("@doctorAppointment")
    public void finish() {
        tearDown();
    }

    @Допустим("врач входит на сайт и попадает к себе на страницу")
    public void врачВходитНаСайтПопадаетКСебеНаСтраницу() throws IOException {
        login(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD);
    }

    @Тогда("врач принимает пациента и создает examinationResult не заполняя поля")
    public void врачПринимаетПациентаИСоздаетExaminationResultНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("15555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("examinationResult")).click();
        webDriver.findElement(By.className("btn-primary")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходит ошибка Обязаельное поле.")
    public void выходитОшибкаОбязаельноеПоле(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("alert-warning")).getText());
    }

    @Тогда("врач принимает пациента и создает diagnoseResult не заполняя поля")
    public void врачПринимаетПациентаИСоздаетDiagnoseResultНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("15555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("diagnoseResult")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходит ошибка Выберите один из вариантов")
    public void выходитОшибкаВыберитеОдинИзВариантов(){
        Assertions.assertEquals("Выберите один из вариантов", webDriver.findElement(By.className("alert-warning")).getText());
    }

    @Тогда("врач принимает пациента и создает sickList не заполняя поля")
    public void врачПринимаетПациентаИСоздаетSickListНеЗаполняяПоля(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("15555555555555")).click();
        webDriver.findElement(By.id("acceptPatient")).click();
        webDriver.findElement(By.id("sickList")).click();
        webDriver.findElement(By.className("btn-primary")).click();
    }

    @И("выходят ошибки Требуется ввести 7 цифр и др")
    public void выходятОшибки(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("startDate")).getText());
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("endDate")).getText());
        Assertions.assertEquals("№ состоит только из цифр :", webDriver.findElement(By.className("sickListNumber")).getText());
    }
    @Тогда("врач принимает пациента и создает sickList заполнив только номер SF")
    public void врачПринимаетПациентаИСоздаетSickListЗаполнивТолькоНомерSF(){
        webDriver.findElement(By.id("allAppointments")).click();
        webDriver.findElement(By.id("15555555555555")).click();
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
    }

}
