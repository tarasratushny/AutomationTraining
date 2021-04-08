package com.miamato;

import com.miamato.listeners.TestReporter;
import com.miamato.listeners.TestResultsListener;
import com.miamato.pageobject.clothstore.LoginPage;
import com.miamato.pageobject.clothstore.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({TestResultsListener.class, TestReporter.class})
public abstract class BaseTest {

    protected WebDriver driver;
    public static final Logger assertLogger = LogManager.getLogger("Assert");

    protected DriverManager driverManager;
    protected PropertyManager propertyManager;

    protected HomePage homePage;
    protected LoginPage loginPage;

    @Parameters({"browserName","testDataFileName"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName
                , @Optional("default.properties") String testDataFileName
                , ITestContext context){
        driverManager = new DriverManager();
        driver = driverManager.getDriver(browserName);
        context.setAttribute("WebDriver", driver);
        //driver.manage().window().maximize();

        propertyManager = new PropertyManager(testDataFileName);
        homePage = new HomePage(driver, propertyManager);
        loginPage = new LoginPage(driver, propertyManager);

    }

    @AfterMethod
    public void browserReset(){
        driver.manage().deleteAllCookies();

    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
