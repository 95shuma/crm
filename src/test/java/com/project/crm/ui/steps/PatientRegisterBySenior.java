package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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


    @Когда("админ ЛПУ заполняет ИНН неправильно1")
    public void заполняетИННнеправильно() {
        webDriver.findElement(By.name("inn")).sendKeys("32345678912345");
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

    @Тогда("выйдет ошибка ИНН состоит только из цифр, начинается с 1 или 2 :")
    public void выходитОшибка1(){
        Assertions.assertEquals("ИНН состоит только из цифр, начинается с 1 или 2 : 32345678912345",  webDriver.findElement(By.xpath("//form[@id='patient']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Когда("админ ЛПУ  заполняет ИНН неправильно2")
    public void заполняетИННнеправильно2() {
        webDriver.findElement(By.name("inn")).sendKeys("1234567891234");
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

    @Тогда("выйдет ошибка Требуется ввести 14 цифр")
    public void выходитОшибка2(){
        Assertions.assertEquals("Требуется ввести 14 цифр",  webDriver.findElement(By.xpath("//form[@id='patient']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Когда("админ ЛПУ  заполняет ИНН неправильно3")
    public void заполняетИННнеправильно3() {
        webDriver.findElement(By.name("inn")).sendKeys("");
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
    @Тогда("выйдет ошибка Обязательное поле")
    public void выходитОшибка3(){
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='ИНН состоит только из цифр, начинается с 1 или 2 : ']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Требуется ввести 14 цифр']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("ИНН состоит только из цифр, начинается с 1 или 2 :");
        expectedAlert.add("Требуется ввести 14 цифр");

        assertEquals(expectedAlert,actualAlert);

    }

    @Когда("админ ЛПУ  добавляет пароль менше 8")
    public void добавляетЗначенияПарольМеньше() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("1234567");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN1234567");
        webDriver.findElement(By.name("surname")).sendKeys("kim");
        webDriver.findElement(By.name("name")).sendKeys("bul");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("1");
        webDriver.findElement(By.name("hospitalId")).sendKeys("1");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("выйдет ошибка Пароль должен содержать минимум 8 символов")
    public void выходятОшибки(){
        Assertions.assertEquals("Пароль должен содержать минимум 8 символов",  webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Пароль должен содержать минимум 8 символов']")).getText());
    }

    @Когда("админ ЛПУ  заполняет пароль неправильно2")
    public void заполняетПарольНеправильно() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("");
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
    @Тогда("выйдет ошибка2 Обязательное поле")
    public void выходитОшибкаОбяз(){
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Пароль должен содержать минимум 8 символов']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("Пароль должен содержать минимум 8 символов");

        assertEquals(expectedAlert,actualAlert);

    }

    @Когда("админ ЛПУ  заполняет имя неправильно")
    public void заполняетИмяНеправильно() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN1234567");
        webDriver.findElement(By.name("surname")).sendKeys("kim");
        webDriver.findElement(By.name("name")).sendKeys("bul222");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("hospitalId")).sendKeys("4");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("выйдет ошибка Имя должно содержать только буквы")
    public void выйдетОшибкаИмяДолжноСодержатьТолькоБуквы(){
        Assertions.assertEquals("Имя должно содержать только буквы : bul222",  webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Имя должно содержать только буквы : bul222']")).getText());
    }


}