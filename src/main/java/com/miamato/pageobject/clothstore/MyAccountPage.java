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

public class MyAccountPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(MyAccountPage.class.getSimpleName());

    @FindBy(xpath = "//h1[contains(text(),'My account')]")
    public WebElement myAccountHeading;
    @FindBy(xpath = "//span[contains(text(),'My addresses')]")
    public WebElement myAddressesButton;


    public MyAccountPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Open My addresses list")
    public MyAccountPage openMyAddressesList(){
        logger.info("Waiting for My Account page to load");
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(myAccountHeading));
        logger.info("Clicking My Addresses button");
        myAddressesButton.click();
        return this;
    }


}
