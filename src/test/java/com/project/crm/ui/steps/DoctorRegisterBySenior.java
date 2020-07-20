package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        login("12222222222222", "12222222222222");
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

    @Когда("админ ЛПУ не заполняет поле № паспорта")
    public void заполняетНеВсеПоля() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912346");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("");
        webDriver.findElement(By.name("surname")).sendKeys("sssss");
        webDriver.findElement(By.name("name")).sendKeys("лшь");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("не добавится, выйдут ошибка1")
    public void неДобавитсяВыйдутОшибка1() {
        List<String> expectedAlert = new ArrayList<String>();
        List<String> actualAlert = new ArrayList<String>();

        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1 passportN'][text()='Обязательное поле']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1 passportN'][text()='№ докумета начинается с AN или ID и состоит из 7 цифр : ']")).getText());
        actualAlert.add(webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1 passportN'][text()='Требуется ввести 9 значений без пробела']")).getText());

        expectedAlert.add("Обязательное поле");
        expectedAlert.add("№ докумета начинается с AN или ID и состоит из 7 цифр :");
        expectedAlert.add("Требуется ввести 9 значений без пробела");

        assertEquals(expectedAlert,actualAlert);
    }
    @Когда("админ ЛПУ заполняет неправильно № паспорта")
    public void заполняетНеправильно() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("123456789");
        webDriver.findElement(By.name("surname")).sendKeys("sssss");
        webDriver.findElement(By.name("name")).sendKeys("лшь");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("не добавится, выйдут ошибка2")
    public void неДобавитсяВыйдутОшибка2() {
        Assertions.assertEquals("№ докумета начинается с AN или ID и состоит из 7 цифр : 123456789", webDriver.findElement(By.className("passportN")).getText());
    }

    @Когда("админ ЛПУ заполняет неправильно № паспорта2")
    public void заполняетНеправильно2() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN 1234567");
        webDriver.findElement(By.name("surname")).sendKeys("sssss");
        webDriver.findElement(By.name("name")).sendKeys("лшь");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("не добавится, выйдут ошибка3")
    public void неДобавитсяВыйдутОшибка3() {
        Assertions.assertEquals("Требуется ввести 9 значений без пробела", webDriver.findElement(By.className("passportN")).getText());
    }
    @Когда("админ ЛПУ  заполняет фамилию неправильно")
    public void заполняетФамилиюНеправильно() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN1234567");
        webDriver.findElement(By.name("surname")).sendKeys("sssss88888");
        webDriver.findElement(By.name("name")).sendKeys("лшь");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("20.01.2010");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }
    @Тогда("выйдет ошибка Фамилия должна содержать только буквы")
    public void выйдетОшибкаФамилияДолжнаСодержатьТолькоБуквы(){
        Assertions.assertEquals("Фамилия должна содержать только буквы : sssss88888",  webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Фамилия должна содержать только буквы : sssss88888']")).getText());
    }

    @Когда("админ ЛПУ  пропустил дату рождения")
    public void пропустилДатуРождения() {
        webDriver.findElement(By.name("inn")).sendKeys("12345678912345");
        webDriver.findElement(By.name("password")).sendKeys("12345678");
        webDriver.findElement(By.name("documentNumber")).sendKeys("AN1234567");
        webDriver.findElement(By.name("surname")).sendKeys("sssss");
        webDriver.findElement(By.name("name")).sendKeys("лшь");
        webDriver.findElement(By.name("middleName")).sendKeys("ait");
        webDriver.findElement(By.name("birthDate")).sendKeys("");
        webDriver.findElement(By.name("gender")).sendKeys("1");
        webDriver.findElement(By.name("placeId")).sendKeys("2");
        webDriver.findElement(By.name("positionId")).sendKeys("3");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("выйдет ошибка Обязательное поле для даты")
    public void ошибкаДаты() {
        Assertions.assertEquals("Обязательное поле",  webDriver.findElement(By.xpath("//div[@class='alert alert-warning mt-1'][text()='Обязательное поле']")).getText());
    }
}