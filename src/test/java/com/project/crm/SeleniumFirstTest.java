package com.project.crm;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SeleniumFirstTest {

    @BeforeClass
    public static  void setUpClass() throws Exception{
        ChromeDriverManager.chromedriver().setup();
    }

    @Test
    public void hasText() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:7777/login");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("11111111111111");
        WebElement element1 = driver.findElement(By.name("password"));
        element1.sendKeys("11111111111111");
        element1.submit();
        assertThat(driver.findElement(By.tagName("h3")));
        driver.close();
    }
}

