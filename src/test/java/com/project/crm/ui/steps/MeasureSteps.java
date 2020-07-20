package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

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
    public void авторизуетсяиОткрываетСписокЛекарств() {
        login("11111111111111", "11111111111111");
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
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/measures")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }
    @Когда("не заполнив поле  нажимает кнопку добавить")
    public void неЗаполнивПолеНажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("появляется ошибка")
    public void появляетсяОшибка() {
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.xpath("//form[@id='measureDos']//div[@class='alert alert-warning mt-1']")).getText());
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