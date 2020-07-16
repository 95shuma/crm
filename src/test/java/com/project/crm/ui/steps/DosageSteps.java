package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class DosageSteps extends Steps {

    @Before("@dosage")
    public void start() {
        ;
        setUp();
    }

    @After("@dosage")
    public void finish() {
        tearDown();
    }

    @Допустим("логинится и открывает список лекарств")
    public void логинитсяиОткрываетСписокЛекарств() {
        login("11111111111111", "11111111111111");
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку доза и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("Доза")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("заполняет все поля и нажимает кнопку добавить")
    public void заполняетВсеПоляиНажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааа");
        webDriver.findElement(By.name("measureId")).sendKeys("аааааа");
        webDriver.findElement(By.name("quantity")).sendKeys("20");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список дозировок")
    public void появляетсяСписокДозировок() {
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/dosages")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("заполняет не все поля и нажимает кнопку добавить")
    public void заполняетНеВсеПоляиНажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.name("measureId")).sendKeys("");
        webDriver.findElement(By.name("quantity")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("сообщения об ошибках")
    public void сообщенияОбОшибках() {
        //Assertions.assertEquals("Название должно содержать только буквы : ", webDriver.findElement(By.xpath("//form[@id='doze']//div[@class='alert alert-warning mt-1']")).getText());
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.xpath("//form[@id='doze']//div[@class='alert alert-warning mt-1']")).getText());
    }
}