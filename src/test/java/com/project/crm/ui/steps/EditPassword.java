package com.project.crm.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EditPassword extends Steps {

    @Before("@editPassword")
    public void start() {
        setUp();
    }

    @After("@editPassword")
    public void finish() {
        tearDown();
    }

    @Допустим("пользователь входит на сайт")
    public void пользовательВходитНаСайт() {
        webDriver.get("http://localhost:7777/login");
    }

    @Когда("пользователь вводит неправильные значения пароля")
    public void пользовательВводитНеправильныеЗначенияПароля() {
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("12222222222222");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("123456789");
        password.submit();
    }

    @Тогда("выходит ошибка Вы ввели неверный ИНН или пароль")
    public void выходитОшибкаВыВвелиНеверныйИННИлиПароль() {
        Assertions.assertEquals("Вы ввели неверный ИНН или пароль", getElementFromLoginForm().getText());
    }

    @Когда("пользователь вводит правильные значения пароля")
    public void пользовательВводитПравильныеЗначенияПароля() {
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("12222222222222");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("12222222222222");
        password.submit();
    }

    @Тогда("входит на страницу и находит пользователя")
    public void входитНаСтраницуИНаходитПользователя() {
        webDriver.get("http://localhost:7777/senior-doctor/users/12222222222222/password");
    }

    @И("меняет пароль")
    public void меняетПароль() {
        WebElement firstPassword = webDriver.findElement(By.name("firstPassword"));
        firstPassword.sendKeys("123456789");
        WebElement secondPassword = webDriver.findElement(By.name("secondPassword"));
        secondPassword.sendKeys("123456789");
        WebElement button = webDriver.findElement(By.id("save-button"));
        button.submit();
    }
    @Когда("пользователь вводит новые значения пароля")
    public void пользовательВводитНовыеЗначенияПароля() {
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("12222222222222");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("123456789");
        password.submit();
    }

    @Тогда("пользователь попадает на свою страницу")
    public void пользовательПопадаетНаСвоюСтраницу() {}

}
