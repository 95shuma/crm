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
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;

public class LoginSteps extends Steps {


    @Before("@loginPage")
    public void start(){
        setUp();
    }

    @After("@loginPage")
    public void finish(){
        tearDown();
    }

    @Допустим("я захожу на главную страницу")
    public void яЗахожуНаГлавнуюСтраницу() throws IOException {
        webDriver.get("http://localhost:7777/");
    }

    @Когда("я не авторизованный пользователь")
    public void яНеАвторизованныйПользователь() {
    }

    @Тогда("кнопка {string} должна быть видна")
    public void кнопкаДолжнаБытьВидна(String arg0) {
        Assertions.assertTrue(getElementByTextFromHeader(arg0).isDisplayed());
    }

    @И("кнопка {string} должна быть скрыта")
    public void кнопкаДолжнаБытьСкрыта(String arg0) {
        Assertions.assertThrows(NoSuchElementException.class, () -> getElementByTextFromHeader(arg0));
    }

    @Когда("я авторизованный пользователь")
    public void яАвторизованныйПользователь()  throws IOException{
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
    }

    @Когда("я введу неверный инн и пароль")
    public void яВведуНеверныйИннИПароль()  throws IOException{
        login("66666666666666","66666666666666");
    }

    @Тогда("высвечуться сообщение ошибки")
    public void высвечутьсяСообщениеОшибки() {
        Assertions.assertEquals("Вы ввели неверный ИНН или пароль", webDriver.findElement(By.xpath("//form[@id='login-form']//div[@class='alert alert-warning mt-1']")).getText());
    }
}
