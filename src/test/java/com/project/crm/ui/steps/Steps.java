package com.project.crm.ui.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {

    WebDriver webDriver;

    protected Steps(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    protected WebElement getElementByTextFromHeader(String text){
        return webDriver.findElement(By.xpath("//nav[@class='site-header sticky-top py-1']//*[contains(text(),'"+ text + "')]"));
    }

    protected WebElement getElementFromLoginForm(){
        return webDriver.findElement(By.xpath("//form[@id='login-form']//div[@class='alert alert-warning mt-1']"));
    }

}
