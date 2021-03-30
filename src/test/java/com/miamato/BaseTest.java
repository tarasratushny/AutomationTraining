package com.miamato;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {

    static WebDriver driver;
    static final String CHROME_DRIVER_PATH = "drivers/windows/chromedriver.exe";
    static SoftAssert softAssert;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void cleanUp(){
            driver.quit();
    }
}
