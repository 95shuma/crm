package com.project.crm.ui.steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DoctorSchedule extends Steps{

    @Допустим("врач входит на сайт")
    public void врачВходитНаСайт() {
        webDriver.get("http://localhost:7777/login");
    }

    @Когда("врач попадает к себе на страницу")
    public void врачПопадаетКСебеНаСтраницу(){
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("33333333333333");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("33333333333333");
        password.submit();
    }

    @Тогда("врач создает рабочий график не заполняя его")
    public void врачСоздаетРабочийГрафикНеЗаполняяЕго(){
        webDriver.get("http://localhost:7777/doctor/schedules/schedule");
        webDriver.findElement(By.className("btn-primary")).submit();
    }
    @И("выходит ошибка Обязаельное поле")
    public void выходитОшибкаОбязаельноеПоле(){
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.className("alert-warning")).getText());
        webDriver.close();
    }
}
