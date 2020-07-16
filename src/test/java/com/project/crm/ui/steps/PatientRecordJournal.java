package com.project.crm.ui.steps;

import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PatientRecordJournal extends Steps{

    @Допустим("пациент входит на сайт")
    public void пациентВходитНаСайт() {
        webDriver.get("http://localhost:7777/login");
    }

    @Ктомуже("изначально врач создает график работы")
    public void изначальноВрачСоздаетГрафикРаботы(){
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("33333333333333");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("33333333333333");
        password.submit();
        webDriver.get("http://localhost:7777/doctor/schedules/schedule");
        WebElement day = webDriver.findElement(By.id("dateId"));
        WebElement dayStartId = webDriver.findElement(By.id("dayStartId"));
        WebElement dayEndId = webDriver.findElement(By.id("dayEndId"));
        WebElement lunchStartId = webDriver.findElement(By.id("lunchStartId"));
        WebElement lunchEndId = webDriver.findElement(By.id("lunchEndId"));
        WebElement button = webDriver.findElement(By.id("button"));
        day.clear();
        day.sendKeys("15.07.2020");
        dayStartId.clear();
        dayStartId.sendKeys("15.07.2020 22:00");
        dayEndId.clear();
        dayEndId.sendKeys("15.07.2020 23:59");
        lunchStartId.clear();
        lunchStartId.sendKeys("16.07.2020 00:00");
        lunchEndId.clear();
        lunchEndId.sendKeys("16.07.2020 00:00");
        button.submit();
    }

    @Когда("пациент попадает к себе на страницу")
    public void пациентПопадаетКСебеНаСтраницу(){
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("55555555555555");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("55555555555555");
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
        webDriver.close();
    }
}
