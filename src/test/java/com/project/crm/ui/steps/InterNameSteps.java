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

public class InterNameSteps extends Steps {

    @Before("@international")
    public void start(){
        setUp();
    }

    @After("@international")
    public void finish(){
        tearDown();
    }

    @Допустим("пользователь авторизуется под админом и открывает список лекарств")
    public void пользовательАвторизуетсяПодАдминомиОткрываетСписокЛекарств() {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку Международное название и кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("Международное название")).click();
        webDriver.findElement(By.name("intName")).click();
    }

    @Когда("пользователь вводит слово из букв и нажимает кнопку добавить")
    public void пользовательВводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("орпорпорпо");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список Международных названий лекарств")
    public void появляетсяСписокМеждународныхНазванийЛекарств() {
        Boolean flag=false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/internationalNames")){
            flag=true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("пользователь вводит слово из букв и цифр и нажимает кнопку добавить")
    public void пользовательВводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("пользователь  ничего не вводит и нажимает кнопку добавить")
    public void пользовательНичегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит сообщение об ошибке")
    public void выходитСообщениеОбОшибке() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444",  webDriver.findElement(By.xpath("//form[@id='commentForm']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Тогда("выходят сообщения об ошибке")
    public void выходятСообщенияОбОшибке() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='commentForm']//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//form[@id='commentForm']//div[@class='alert alert-warning mt-1'][text()='Название должно содержать только буквы : ']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Название должно содержать только буквы :");

        assertEquals(expectedAlert,actualAlert);
    }

}
