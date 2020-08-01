package com.project.crm.ui.steps;

import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PatientAccept extends Steps{

    @Before("@test")
    public void start() {
        setUp();
    }

    @After("@test")
    public void finish() {
        tearDown();
    }

    @Допустим("врач входит на свою страницу")
    public void врачВходитНаСвоюСтраницу() throws IOException {
        login(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD);
    }


    @Когда("врач создает рабочий график")
    public void врачСоздаетРабочийГрафик(){
        webDriver.get("http://localhost:7777/doctor/schedules/schedule");
        webDriver.findElement(By.name("date")).sendKeys("20.08.2020");
        //webDriver.findElement(By.name("regJournalId")).sendKeys("");
        webDriver.findElement(By.name("dayStart")).sendKeys("20.09.2020 08:00:00");
        webDriver.findElement(By.name("dayEnd")).sendKeys("21.09.2020 08:00:00");
        webDriver.findElement(By.name("lunchStart")).sendKeys("20.09.2020 13:00:00");
        webDriver.findElement(By.name("lunchEnd")).sendKeys("20.09.2020 14:00:00");
        webDriver.findElement(By.className("btn-primary")).submit();
    }
    @Тогда("результат появляется в списке графиков")
    public void появляетсяСписок() {
        webDriver.get("http://localhost:7777/doctor/schedules");
    }

    @Допустим("пациент входит на свою страницу")
    public void пациентВходитНаСайт()  throws IOException {
        login(Constants.PATIENT_INN,Constants.PATIENT_PASSWORD);
    }

    @Когда("записывается к врачу")
    public void записываетсяКврачу(){
        webDriver.get("http://localhost:7777/patient/records/medicalHistory");
        webDriver.findElement(By.className("btn-primary")).submit();
        webDriver.get("http://localhost:7777/patient/records/hospital");
        webDriver.get("http://localhost:7777/patient/records/doctor");
        webDriver.get("http://localhost:7777/patient/records/record");
        webDriver.findElement(By.name("reason")).sendKeys("рпрпрпрпр");
        webDriver.findElement(By.name("dateTime")).sendKeys("20.09.2020 08:00:00");
        webDriver.findElement(By.className("btn-primary")).submit();
    }

    @Тогда("результат появляется в списке записей")
    public void результатПоявляетсяВспискеЗаписей(){
        webDriver.get("http://localhost:7777/patient/records");
    }

    @И("открывает список записей")
    public void открываетСписокЗаписей(){
        webDriver.get("http://localhost:7777/doctor/records");
    }

    @Когда("выбирает пациента")
    public void выбыираетПациента(){
        webDriver.findElement(By.linkText("Просмотрен")).click();
    }

 /*   @Тогда("открывается форма для заполнения истории болезни")
    public void принимаетПациента(){
        webDriver.get("http://localhost:7777/doctor/records/3/accept");
        webDriver.findElement(By.className("btn-primary")).submit();
    }
    @И("заполненные данные сохранятся")
    public void данныеСохранятся(){
        webDriver.get("http://localhost:7777/doctor/records");
    }*/
}
