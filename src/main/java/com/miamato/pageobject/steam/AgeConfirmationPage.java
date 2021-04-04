package com.miamato.pageobject.steam;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgeConfirmationPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AgeConfirmationPage.class.getSimpleName());

    @FindBy(xpath = "//select[@id='ageDay']")
    public WebElement BIRTH_DAY_DROPDOWN;

    @FindBy(xpath = "//select[@id='ageMonth']")
    public WebElement BIRTH_MONTH_DROPDOWN;

    @FindBy(xpath = "//select[@id='ageYear']")
    public WebElement BIRTH_YEAR_DROPDOWN;

    @FindBy(xpath = "//div[@class='agegate_text_container btns']/a[1]")
    public WebElement VIEW_PAGE_BUTTON;

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
        selectFromDropdownByValue(BIRTH_DAY_DROPDOWN, day, logger);
        return this;
    }

    @Step("Selecting birth month as: {month}")
    public AgeConfirmationPage selectBirthMonth(String month){
        logger.info("Selecting birth month");
        selectFromDropdownByValue(BIRTH_MONTH_DROPDOWN, month, logger);
        return this;
    }

    @Step("Selecting birth year as: {year}")
    public AgeConfirmationPage selectBirthYear(String year){
        logger.info("Selecting birth year");
        selectFromDropdownByValue(BIRTH_YEAR_DROPDOWN, year, logger);
        return this;
    }

    @Step("Press View Page button")
    public AgeConfirmationPage confirmCustomerAge(){
        logger.info("Checking if you are eligible for accessing target page");
        clickOnElement(VIEW_PAGE_BUTTON, logger);
        return this;
    }
}
