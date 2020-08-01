package com.project.crm.ui.steps;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;

public class Steps {

    WebDriver webDriver;

    public void setUp() throws NoSuchElementException {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless", "--window-size=1920,1200", "--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        webDriver = new ChromeDriver(chromeOptions);
       // webDriver.get("http://seleniumhq.org");
    }

    public void tearDown(){
        webDriver.close();
    }

    public void login(String name, String pass) throws IOException {
     try{   webDriver.get("http://localhost:7777/");
        System.out.println("открылась главная страница" +webDriver.getTitle());
        webDriver.findElement(By.name("username")).sendKeys(name);
        webDriver.findElement(By.name("password")).sendKeys(pass);
        webDriver.findElement(By.name("login")).click();
    }catch(Exception e){
        File screenshotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("D:\\SoftwareTestingMaterial.png"));//скрин ошибки при логине
         System.out.println("скрин ошибки сохранена в папке Д");
    }
    }



    @AfterMethod //этот метод выполняется после каждого теста
    public void screenShot(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot screenshot=(TakesScreenshot)webDriver;
                File src=screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File("D:\\"+result.getName()+".png"));
                System.out.println("Successfully captured a screenshot");
            }catch (Exception e){
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }

    protected WebElement getElementByTextFromHeader(String text){
        return webDriver.findElement(By.xpath("//nav[@class='site-header sticky-top py-1']//*[contains(text(),'"+ text + "')]"));
    }

    protected WebElement getElementFromLoginForm(){
        return webDriver.findElement(By.xpath("//form[@id='login-form']//div[@class='alert alert-warning mt-1']"));
    }


}
