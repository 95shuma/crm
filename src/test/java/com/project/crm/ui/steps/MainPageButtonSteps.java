package com.project.crm.ui.steps;

import com.project.crm.backend.util.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;

import java.io.IOException;

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
    public void администраторАвторизуется() throws IOException {
        login(Constants.ADMIN_DEV_INN,Constants.ADMIN_DEV_PASSWORD);
    }

    @Затем("нажимает на кнопку Список ЛПУ")
    public void нажимаетКнопкуПоказать() {
        webDriver.findElement(By.linkText("Список ЛПУ")).click();
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
    public void админЛПУавторизуется() throws IOException {
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
    public void докторАвторизуется() throws IOException {
        login(Constants.DOCTOR_INN, Constants.DOCTOR_PASSWORD);
    }

    @Затем("нажимает на кнопку Все записи")
    public void нажимаетКнопкуПросмотр() {
        webDriver.findElement(By.linkText("Все записи")).click();
    }

    @Когда("нажимает на кнопку Главная страница")
    public void нажимаетКнопкуГлавная() {
        webDriver.findElement(By.id("main")).click();
    }

    @Тогда("перейдет в главную стр-цу доктора")
    public void перейдетНаГлавнуюСтраницу() {
        webDriver.get("http://localhost:7777/doctor");
    }

    //проверка 4 роли

    @Допустим("пациент авторизуется")
    public void пациентВходитНаСайт() throws IOException {
        login(Constants.PATIENT_INN,Constants.PATIENT_PASSWORD);
    }

    @Затем("нажимает на кнопку Записаться к врачу")
    public void нажимаетКнопкуЗаписаться() {
        webDriver.findElement(By.linkText("Записаться к врачу")).click();
    }

    @Когда("нажимает на кнопку Главная стр-ца")
    public void нажимаетКнопкуГлавнаяСтр() {
        webDriver.findElement(By.id("main")).click();
    }

    @Тогда("перейдет в главную стр-цу пациента")
    public void перейдетНаГлавнуюСтраницуПациента() {
        webDriver.get("http://localhost:7777/patient");
    }
}
