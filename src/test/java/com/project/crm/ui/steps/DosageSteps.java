package com.project.crm.ui.steps;


import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class DosageSteps extends Steps {

    @Before("@dosage")
    public void start() {
        setUp();
    }

    @After("@dosage")
    public void finish() {
        tearDown();
    }

    @Допустим("логинится и открывает список лекарств")
    public void логинитсяиОткрываетСписокЛекарств() {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
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
        webDriver.get("http://localhost:7777/admin/dosages");
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
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.xpath("//form[@id='doze']//div[@class='alert alert-warning mt-1']")).getText());
    }
}