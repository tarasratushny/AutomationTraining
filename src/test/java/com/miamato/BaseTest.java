package com.miamato;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({TestResultsListener.class,TestReporter.class})
public abstract class BaseTest {

    public static WebDriver driver;
    public static final Logger logger = LogManager.getLogger("");

    @Parameters("browserName")
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName){
        driver = DriverManager.getDriver(browserName);
        //driver.manage().window().maximize();
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
