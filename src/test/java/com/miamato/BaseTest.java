package com.miamato;

import com.miamato.pageobject.steam.AgeConfirmationPage;
import com.miamato.pageobject.steam.HomePage;
import com.miamato.pageobject.steam.SearchResultsPage;
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
    public static final Logger assertLogger = LogManager.getLogger("Assert");

    protected HomePage homePage = null;
    protected SearchResultsPage searchResultsPage = null;
    protected AgeConfirmationPage ageConfirmationPage = null;

    @Parameters("browserName")
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName){
        driver = DriverManager.getDriver(browserName);
        //driver.manage().window().maximize();

        homePage = HomePage.getInstance(driver);
        searchResultsPage = SearchResultsPage.getInstance(driver);
        ageConfirmationPage = AgeConfirmationPage.getInstance(driver);
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
