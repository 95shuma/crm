package com.project.crm.ui.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class RemedyFormSteps extends Steps {

    @Before("@form")
    public void start(){;
        setUp();
    }

    @After("@form")
    public void finish(){
        tearDown();
    }

    @Допустим("админ логинится и открывает список лекарств")
    public void админАвторизуетсяиОткрываетСписокЛекарств() {
        login("11111111111111","11111111111111");
        webDriver.findElement(By.name("remedies")).click();
    }

    @Затем("нажимает на ссылку форма и ниже кнопку добавить")
    public void нажимаетКнопкуДобавить() {
        webDriver.findElement(By.linkText("форма")).click();
        webDriver.findElement(By.xpath("//a[@class='btn btn-primary w-100']")).click();
    }

    @Когда("вводит слово из букв и нажимает кнопку добавить")
    public void вводитСловоИзБуквИНажимаетДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("аааааабббб");
        webDriver.findElement(By.name("name")).submit();
    }

    @Тогда("появляется список лекарственных форм")
    public void появляетсяСписокФорм() {
        Boolean flag=false;
        if (webDriver.getCurrentUrl().equals("http://localhost:7777/admin/forms")){
            flag=true;
        };
        Assertions.assertTrue(flag);
    }

    @Когда("вводит слово из букв и цифр и нажимает кнопку добавить")
    public void вводитСловоИзБуквИцифрИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("letters4444");
        webDriver.findElement(By.className("modal-footer")).submit();
    }

    @Когда("ничего не вводит и нажимает кнопку добавить")
    public void ничегоНеВводитИнажимаетКнопкуДобавить() {
        webDriver.findElement(By.name("name")).sendKeys("");
        webDriver.findElement(By.className("modal-footer")).submit();
    }
    @Тогда("выходит ошибка Обязательное поле")
    public void выходитОшибкаОбязательноеПоле() {
        Assertions.assertEquals("Название должно содержать только буквы : letters4444", webDriver.findElement(By.xpath("//form[@id='remedyForm']//div[@class='alert alert-warning mt-1']")).getText());
    }

    @Тогда("выходят уведомление об ошибках")
    public void выходятУведомлениеОбОшибках() {
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.xpath("//form[@id='remedyForm']//div[@class='alert alert-warning mt-1']")).getText());
    }

}
