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

public class DoctorSchedule extends Steps{

    @Before("@doctorSchedule")
    public void start() {
        setUp();
    }

    @After("@doctorSchedule")
    public void finish() {
        tearDown();
    }

    @Допустим("врач входит на сайт")
    public void врачВходитНаСайт() {
        login(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD);
    }


    @Тогда("врач создает рабочий график не заполняя его")
    public void врачСоздаетРабочийГрафикНеЗаполняяЕго(){
        webDriver.get("http://localhost:7777/doctor/schedules/schedule");
        webDriver.findElement(By.className("btn-primary")).submit();
    }
    @И("выходит ошибка Обязаельное поле")
    public void выходитОшибкаОбязаельноеПоле(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("alert-warning")).getText());
    }
}
