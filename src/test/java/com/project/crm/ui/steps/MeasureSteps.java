package com.project.crm.ui.steps;


import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeasureSteps extends Steps {

    @Before("@measure")
    public void start() {
        setUp();
    }

    @After("@measure")
    public void finish() {
        tearDown();
    }
    @Допустим("авторизуется и открывает список лекарств")
    public void авторизуетсяиОткрываетСписокЛекарств() throws IOException {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку дозы")
    public void нажимаетНаСсылкуДозы() {
        webDriver.findElement(By.linkText("Доза")).click();
    }
    @Затем("нажимает на ссылку единицы измерения и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("Единица измерения")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }
    @Когда("заполняет поле и нажимает кнопку добавить")
    public void заполняетПолеиНажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("миллиграмм");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("появляется список единиц измерений")
    public void появляетсяСписокЕдиницИзмерений() {
        webDriver.get("http://localhost:7777/admin/measures");
    }
    @Когда("не заполнив поле  нажимает кнопку добавить")
    public void неЗаполнивПолеНажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("появляется ошибка")
    public void появляетсяОшибка() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='measureDos']//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='measureDos']//div[@class='alert alert-warning mt-1'][text()='Название должно содержать только буквы : ']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Название должно содержать только буквы :");

        assertEquals(expectedAlert,actualAlert);
    }

    @Когда("добавляет слово из букв и цифр")
    public void добавляетСловоИзБуквИцифр() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка о названии")
    public void выходитОшибка() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", webDriver.findElement(By.xpath("//form[@id='measureDos']//div[@class='alert alert-warning mt-1']")).getText());
    }
}