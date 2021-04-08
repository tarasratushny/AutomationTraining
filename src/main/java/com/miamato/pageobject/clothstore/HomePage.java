package com.miamato.pageobject.clothstore;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class.getSimpleName());
    private final String HOME_PAGE_URL = propertyManager.getProperty("homepage.url");

    @FindBy(xpath = "//a[@class='login']")
    public WebElement loginButton;

    public HomePage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Open application home page")
    public HomePage open(){
        logger.info("Trying to open application home page");
        driver.navigate().to(HOME_PAGE_URL);
        return this;
    }

    @Step("Click on login button")
    public HomePage clickLoginButton(){
        logger.info("Trying to click on login button on homepage");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(loginButton)).click();
        return this;
    }

}
