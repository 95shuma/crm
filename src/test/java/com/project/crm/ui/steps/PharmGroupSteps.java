package com.project.crm.ui.steps;


import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PharmGroupSteps extends Steps {

    @Before("@group")
    public void start(){
        setUp();
    }

    @After("@group")
    public void finish(){
        tearDown();
    }

    @Допустим("админ авторизуется и открывает список лекарств")
    public void админАвторизуетсяиОткрываетСписокЛекарств() throws NoSuchElementException, IOException {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку фарм группа и кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("фарм группа")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("админ вводит слово из букв и нажимает кнопку добавить")
    public void админВводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааа");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список фарм групп лекарств")
    public void появляетсяСписокФармГруппЛекарств() {
        webDriver.get("http://localhost:7777/admin/groups");
    }

    @Когда("админ вводит слово из букв и цифр и нажимает кнопку добавить")
    public void админВводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("админ  ничего не вводит и нажимает кнопку добавить")
    public void админНичегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка")
    public void выходитОшибка() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", webDriver.findElement(By.xpath("//form[@id='group']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Тогда("выходят ошибки")
    public void выходятОшибки() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='group']//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='group']//div[@class='alert alert-warning mt-1'][text()='Название должно содержать только буквы : ']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Название должно содержать только буквы :");

        assertEquals(expectedAlert,actualAlert);
       // Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.xpath("//form[@id='group']//div[@class='alert alert-warning mt-1']")).getText());
    }

}
