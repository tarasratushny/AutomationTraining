package com.miamato;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({TestResultsListener.class,TestReporter.class})
public abstract class BaseTest {

    //public static WebDriver driver;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger("");

    @Parameters("browserName")
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName){
        setupDriverForBrowser(browserName);
        //driver.manage().window().maximize();
    }

    @AfterClass
    public void cleanUp(){
        driver.get().quit();
        driver.remove();
    }


    private void setupDriverForBrowser(String browserName) {
        switch (browserName.toUpperCase(Locale.ROOT)){
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            }
            case "EDGE" -> {
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
            }
        }
    }
}
