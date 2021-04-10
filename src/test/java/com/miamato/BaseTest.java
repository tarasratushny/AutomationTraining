package com.miamato;

import com.miamato.listeners.TestReporter;
import com.miamato.listeners.TestResultsListener;
import com.miamato.pageobject.clothstore.LoginPage;
import com.miamato.pageobject.clothstore.HomePage;
import com.miamato.pageobject.clothstore.MyAccountPage;
import com.miamato.pageobject.clothstore.MyAddressesPage;
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

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final Logger assertLogger = LogManager.getLogger("Assert");

    protected DriverManager driverManager;
    protected PropertyManager propertyManager;

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected MyAddressesPage myAddressesPage;
    protected MyAccountPage myAccountPage;

    @Parameters({"browserName","testDataFileName"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName
                , @Optional("clothsStore.properties") String testDataFileName
                , ITestContext context){
        driverManager = new DriverManager();
        driver.set(driverManager.getDriver(browserName));
        context.setAttribute("WebDriver", driver.get());

        propertyManager = new PropertyManager(testDataFileName);
        homePage = new HomePage(driver.get(), propertyManager);
        loginPage = new LoginPage(driver.get(), propertyManager);
        myAccountPage = new MyAccountPage(driver.get(), propertyManager);
        myAddressesPage = new MyAddressesPage(driver.get(), propertyManager);
    }

    @AfterMethod
    public void browserReset(){
        driver.get().manage().deleteAllCookies();

    }

    @AfterClass
    public void cleanUp(){
        driver.get().quit();
    }
}
