package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class DoctorRegisterBySenior extends Steps {

    @Before("@doctor")
    public void start() {
        setUp();
    }

    @After("@doctor")
    public void finish() {
        tearDown();
    }

    @Допустим("админ ЛПУ авторизуется")
    public void админЛПУавторизуется() {
        login("22222222222222", "22222222222222");
    }

    @Затем("нажимает на кнопку Зарегистрировать доктора")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.className("doctors")).click();
    }

    @Когда("админ ЛПУ заполняет все поля")
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
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("hospitalId")).sendKeys("4");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("перейдет в главную страницу админа ЛПУ")
    public void перейдетвГлавнуюСтраницуАдминаЛПУ() {
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/senior-doctor")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }
    @И("результат появляется в списке докторов")
    public void результатПоявляетсявСпискеДокторов() {
        webDriver.findElement(By.linkText("Список докторов")).click();
        Boolean flag = false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/senior-doctor/doctors")) {
            flag = true;
        };
        Assertions.assertTrue(flag);
    }

}