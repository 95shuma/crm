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

public class AdminAddHospitalSteps extends Steps{

    @Before("@place")
    public void start(){
        setUp();
    }

    @After("@place")
    public void finish(){
        tearDown();
    }

    @Допустим("администратор авторизуется.")
    public void администраторАвторизуется() {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
    }

    @И("нажимает на кнопку зарегистрировать новое место")
    public void нажимаетНаКнопкуЗарегистрироватьНовоеМесто() {
        webDriver.findElement(By.name("hospital")).click();
    }

    @Когда("администратор правильно заполняет все поля нажимает сохранить")
    public void администраторПравильноЗаполняетВсеПоляНажимаетСохранить() {
        webDriver.findElement(By.name("name")).sendKeys("Ломоносова");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("street")).sendKeys("Ломоносова");
        webDriver.findElement(By.name("houseNum")).sendKeys("10");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("администратор обратно переходит в панель администратора.")
    public void администраторОбратноПереходитВПанельАдминистратора() {
        webDriver.get("http://localhost:7777/admin");
    }
}
