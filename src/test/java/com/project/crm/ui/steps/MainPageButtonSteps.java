package com.project.crm.ui.steps;

import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;

public class MainPageButtonSteps extends Steps{

    @Before("@button")
    public void start(){
        setUp();
    }

    @After("@button")
    public void finish(){
        tearDown();
    }

    //проверка 1 роли
    @Допустим("Администратор авторизуется")
    public void администраторАвторизуется() {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
    }

    @Затем("нажимает на кнопку Показать список ЛПУ")
    public void нажимаетКнопкуПоказать() {
        webDriver.findElement(By.linkText("Показать список ЛПУ")).click();
    }

    @Когда("нажимает на ссылку \"Главная страница\"")
    public void нажимаетСсылкуГлав() {
        webDriver.findElement(By.id("main")).click();
    }
    @Тогда("перейдет в главную страницу админа")
    public void перейдетНаГлавную() {
        webDriver.get("http://localhost:7777/admin");
    }

    //проверка 2 роли
    @Допустим("Админ ЛПУ авторизуется")
    public void админЛПУавторизуется() {
        login(Constants.SENIOR_DOCTOR_INN, Constants.SENIOR_DOCTOR_PASSWORD);
    }

    @Затем("нажимает на кнопку Список докторов")
    public void нажимаетКнопкуСписокДокторов() {
        webDriver.findElement(By.linkText("Список докторов")).click();
    }

    @Когда("нажимает на ссылку Главная страница")
    public void нажимаетСсылкуГлавная() {
        webDriver.findElement(By.id("main")).click();
    }

    @Тогда("перейдет в главную стр-цу админа ЛПУ")
    public void перейдетНаГлавнуюСтр() {
        webDriver.get("http://localhost:7777/senior-doctor");
    }

    //проверка 3 роли
    @Допустим("доктор авторизуется")
    public void докторАвторизуется() {
        login(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD);
    }

    @Затем("нажимает на кнопку Просмотр всех записей")
    public void нажимаетКнопкуПросмотр() {
        webDriver.findElement(By.linkText("Просмотр всех записей")).click();
    }

    @Когда("нажимает на кнопку Главная страница")
    public void нажимаетКнопкуГлавная() {
        webDriver.findElement(By.id("main")).click();
    }

    @Тогда("перейдет в главную стр-цу доктора")
    public void перейдетНаГлавнуюСтраницу() {
        webDriver.get("http://localhost:7777/doctor");
    }


}
