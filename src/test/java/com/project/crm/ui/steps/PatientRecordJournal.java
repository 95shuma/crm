package com.project.crm.ui.steps;

import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PatientRecordJournal extends Steps{

    @Before("@patientRecordJournal")
    public void start() {
        setUp();
    }

    @After("@patientRecordJournal")
    public void finish() {
        tearDown();
    }

    @Допустим("пациент входит на сайт и попадает к себе на страницу")
    public void пациентВходитНаСайт() throws IOException {
        login(Constants.PATIENT_INN,Constants.PATIENT_PASSWORD);
    }

    @Когда("пациент записывается к врачу не заполняя поле жалоба")
    public void пациентЗаписываетсяКВрачуНеЗаполняяПолеЖалоба(){
        webDriver.get("http://localhost:7777/patient/records/record");
        webDriver.findElement(By.className("btn-primary")).submit();
    }

    @Тогда ("выходит ошибка Укажите причину")
    public void выходитОшибкаУкажитеПричину(){
        Assertions.assertEquals("Укажите причину", webDriver.findElement(By.id("indicateReason")).getText());
        Assertions.assertEquals("Выберите подходящее время и дату записи", webDriver.findElement(By.id("indicateDateTime")).getText());
    }

}
