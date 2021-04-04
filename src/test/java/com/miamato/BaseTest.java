package com.miamato;

import com.miamato.listeners.TestReporter;
import com.miamato.listeners.TestResultsListener;
import com.miamato.pageobject.steam.AgeConfirmationPage;
import com.miamato.pageobject.steam.HomePage;
import com.miamato.pageobject.steam.ProductDetailsPage;
import com.miamato.pageobject.steam.SearchResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({TestResultsListener.class, TestReporter.class})
public abstract class BaseTest {

    protected WebDriver driver;
    public static final Logger assertLogger = LogManager.getLogger("Assert");

    protected HomePage homePage = null;
    protected SearchResultsPage searchResultsPage = null;
    protected AgeConfirmationPage ageConfirmationPage = null;
    protected ProductDetailsPage productDetailsPage = null;
    protected DriverManager driverManager = null;

    @Parameters("browserName")
    @BeforeClass
    public void setup(@Optional("Firefox") String browserName, ITestContext context){
        driverManager = new DriverManager();
        driver = driverManager.getDriver(browserName);
        context.setAttribute("WebDriver", driver);
        //driver.manage().window().maximize();

        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        ageConfirmationPage = new AgeConfirmationPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
