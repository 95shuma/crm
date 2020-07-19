package com.project.crm.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PatientRecordJournal extends Steps{

    @Before("@patientRecordJournal")
    public void start() {
        setUp();
    }

    @After("@patientRecordJournal")
    public void finish() {
        tearDown();
    }

    @Допустим("пациент входит на сайт")
    public void пациентВходитНаСайт() {
        webDriver.get("http://localhost:7777/login");
    }

    @Когда("пациент попадает к себе на страницу")
    public void пациентПопадаетКСебеНаСтраницу(){
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("15555555555555");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("15555555555555");
        password.submit();
    }

    @Тогда("пациент записывается к врачу не заполняя поле жалоба")
    public void пациентЗаписываетсяКВрачуНеЗаполняяПолеЖалоба(){
        webDriver.get("http://localhost:7777/patient/records/record");
        webDriver.findElement(By.className("btn-primary")).submit();
    }
    @И("выходит ошибка Укажите причину")
    public void выходитОшибкаУкажитеПричину(){
        Assertions.assertEquals("Укажите причину", webDriver.findElement(By.id("indicateReason")).getText());
        Assertions.assertEquals("Выберите подходящее время и дату записи", webDriver.findElement(By.id("indicateDateTime")).getText());
    }
}
