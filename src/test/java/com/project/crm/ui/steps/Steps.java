package com.project.crm.ui.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {

    WebDriver webDriver;

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    public void tearDown(){
        webDriver.close();
    }

    public void login(String name, String pass){
        webDriver.get("http://localhost:7777/");
        webDriver.findElement(By.name("username")).sendKeys(name);
        webDriver.findElement(By.name("password")).sendKeys(pass);
        webDriver.findElement(By.name("login")).click();
    }

    protected WebElement getElementByTextFromHeader(String text){
        return webDriver.findElement(By.xpath("//nav[@class='site-header sticky-top py-1']//*[contains(text(),'"+ text + "')]"));
    }

    protected WebElement getElementFromLoginForm(){
        return webDriver.findElement(By.xpath("//form[@id='login-form']//div[@class='alert alert-warning mt-1']"));
    }

}
