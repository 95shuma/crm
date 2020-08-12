package com.project.crm.ui.steps;

import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.IOException;

public class RegSeniorAdminSteps extends Steps{

    @Before("@group")
    public void start(){
        setUp();
    }

    @After("@group")
    public void finish(){
        tearDown();
    }

    @Допустим("администратор авторизуется")
    public void администраторАвторизуется() throws IOException {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
    }

    @И("нажимает на кнопку зарегистрировать админа ЛПУ")
    public void нажимаетНаКнопкуЗарегистрироватьАдминаЛПУ() {
        webDriver.findElement(By.name("regSeniorAdmin")).click();
    }

    @Когда("администратор заполняет все поля правильно и нажимает на кнопку Добавить")
    public void администраторЗаполняетВсеПоляПравильноИНажимаетНаКнопкуДобавить() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678901234");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("ID1234567");
        webDriver.findElement(By.name("name")).sendKeys("Роберт");
        webDriver.findElement(By.name("surname")).sendKeys("Робертов");
        webDriver.findElement(By.name("middleName")).sendKeys("Робертович");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("администратор обратно переходит в панель администратора")
    public void администраторОбратноПереходитВПанельАдминистратора() {
        webDriver.get("http://localhost:7777/admin");
    }

    @Когда("администратор не заполняет обязательные поля")
    public void администраторНеЗаполняетОбязательныеПоля() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678901234");
        webDriver.findElement(By.name("documentNumber")).sendKeys("ID1234567");
        webDriver.findElement(By.name("name")).sendKeys("Роберт");
        webDriver.findElement(By.name("surname")).sendKeys("Робертов");
        webDriver.findElement(By.name("middleName")).sendKeys("Робертович");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется ошибка Обязательное поле")
    public void появляетсяОшибкаОбязательноеПоле() {
        Assertions.assertEquals("Обязательное поле",  webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
    }
}
