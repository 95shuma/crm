package com.project.crm.ui.steps;


import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RemedyTypeSteps extends Steps {

    @Before("@type")
    public void start(){
        setUp();
    }

    @After("@type")
    public void finish(){
        tearDown();
    }

    @Допустим("он логинится и открывает список лекарств")
    public void АвторизуетсяиОткрываетСписокЛекарств() {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку тип и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("тип")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("вводит значение из букв и нажимает кнопку добавить")
    public void вводитЗначениеИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааабббб");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список типов")
    public void появляетсяСписокТипов() {
        webDriver.get("http://localhost:7777/admin/remedyTypes");
    }

    @Когда("вводит значение из букв и цифр и нажимает кнопку добавить")
    public void вводитЗначениеИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("не заполняет и нажимает кнопку добавить")
    public void ничегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка об Обязательном поле")
    public void выходитОшибкаОбОбязательномПоле() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Название должно содержать только буквы : ']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Название должно содержать только буквы :");

        assertEquals(expectedAlert,actualAlert);
    }

    @Тогда("выходят уведомления об ошибках")
    public void выходятУведомленияОбОшибках() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444",  webDriver.findElement(By.xpath("//form[@id='type']//div[@class='alert alert-warning mt-1']")).getText());
    }

}
