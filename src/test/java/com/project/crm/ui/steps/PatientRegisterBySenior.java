package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class PatientRegisterBySenior extends Steps {

    @Before("@patient")
    public void start() {
        setUp();
    }

    @After("@patient")
    public void finish() {
        tearDown();
    }

    @Допустим("админ ЛПУ логинится")
    public void админЛПУлогинится() {
        login("22222222222222", "22222222222222");
    }

    @Затем("нажимает на кнопку Зарегистрировать пациента")
    public void нажимаетКнопкуЗарегистрировать() {
        webDriver.findElement(By.linkText("Зарегистрировать пациента")).click();
    }

    @Когда("админ ЛПУ заполняет все поля регистрации")
    public void заполняетВсеПоля() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN1234567");
        webDriver.findElement(By.name("surname")).sendKeys("kim");
        webDriver.findElement(By.name("name")).sendKeys("bul");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("hospitalId")).sendKeys("4");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("попадет в главную страницу админа ЛПУ")
    public void попадетвГлавнуюСтраницуАдминаЛПУ() {
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/senior-doctor")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }

    @И("результат появляется в списке пациентов")
    public void результатПоявляетсявСпискеПациентов() {
        webDriver.findElement(By.linkText("Список пациентов")).click();
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/senior-doctor/patients")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }

}