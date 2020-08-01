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

public class RemedyFormSteps extends Steps {

    @Before("@form")
    public void start(){;
        setUp();
    }

    @After("@form")
    public void finish(){
        tearDown();
    }

    @Допустим("админ логинится и открывает список лекарств")
    public void админАвторизуетсяиОткрываетСписокЛекарств()  throws IOException {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку форма и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("форма")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("вводит слово из букв и нажимает кнопку добавить")
    public void вводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааабббб");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список лекарственных форм")
    public void появляетсяСписокФорм() {
        webDriver.get("http://localhost:7777/admin/forms");
    }

    @Когда("вводит слово из букв и цифр и нажимает кнопку добавить")
    public void вводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("ничего не вводит и нажимает кнопку добавить")
    public void ничегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка Обязательное поле")
    public void выходитОшибкаОбязательноеПоле() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", webDriver.findElement(By.xpath("//form[@id='remedyForm']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Тогда("выходят уведомление об ошибках")
    public void выходятУведомлениеОбОшибках() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Название должно содержать только буквы : ']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Название должно содержать только буквы :");

        assertEquals(expectedAlert,actualAlert);
    }

}
