package com.miamato.pageobject.steam;

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

public class AgeConfirmationPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AgeConfirmationPage.class.getSimpleName());

    @FindBy(xpath = "//select[@id='ageDay']")
    public WebElement birthDay;

    @FindBy(xpath = "//select[@id='ageMonth']")
    public WebElement birthMonth;

    @FindBy(xpath = "//select[@id='ageYear']")
    public WebElement birthYear;

    @FindBy(xpath = "//div[@class='agegate_text_container btns']/a[1]")
    public WebElement viewPageButton;

    @FindBy(xpath = "//div[@class='newmodal_content']/div")
    public WebElement errorMessageAgeInvalid;

    @FindBy(xpath = "//div[contains(@class,'btn_grey_steamui')]")
    public WebElement errorMessageOkButton;


    public AgeConfirmationPage(WebDriver driver, PropertyManager propertyManager){
        super(driver, propertyManager);
    }

    @Step("Selecting birth date as: {day}/{month}/{year}")
    public AgeConfirmationPage selectBirthDate(String day, String month, String year){
        selectBirthDay(day);
        selectBirthMonth(month);
        selectBirthYear(year);
        return this;
    }

    @Step("Selecting birth day as: {day}")
    public AgeConfirmationPage selectBirthDay(String day){
        logger.info("Selecting birth day");
        selectFromDropdownByValue(birthDay, day, logger);
        return this;
    }

    @Step("Selecting birth month as: {month}")
    public AgeConfirmationPage selectBirthMonth(String month){
        logger.info("Selecting birth month");
        selectFromDropdownByValue(birthMonth, month, logger);
        return this;
    }

    @Step("Selecting birth year as: {year}")
    public AgeConfirmationPage selectBirthYear(String year){
        logger.info("Selecting birth year");
        selectFromDropdownByValue(birthYear, year, logger);
        return this;
    }

    @Step("Press View Page button")
    public AgeConfirmationPage confirmCustomerAge(){
        logger.info("Checking if you are eligible for accessing target page");
        viewPageButton.click();
        return this;
    }

    @Step("Press OK button on age validation error modal window")
    public AgeConfirmationPage closeAgeValidationErrorModal() {
        logger.info("Closing error modal window after failed age validation");
        //errorMessageOkButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
            ExpectedConditions.elementToBeClickable(errorMessageOkButton)).click();
        return this;
    }


}
