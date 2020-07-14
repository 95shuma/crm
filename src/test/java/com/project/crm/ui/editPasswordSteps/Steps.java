package com.project.crm.ui.editPasswordSteps;

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

    protected WebElement getElementFromLoginForm(){
        return webDriver.findElement(By.xpath("//form[@id='login-form']//div[@class='alert alert-warning mt-1']"));
    }

}
