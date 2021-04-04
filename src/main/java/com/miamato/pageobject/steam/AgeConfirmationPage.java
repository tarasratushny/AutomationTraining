package com.miamato.pageobject.steam;

import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgeConfirmationPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AgeConfirmationPage.class.getSimpleName());

    public static final By BIRTH_DAY_DROPDOWN = By.xpath("//select[@id='ageDay']");
    public static final By BIRTH_MONTH_DROPDOWN = By.xpath("//select[@id='ageMonth']");
    public static final By BIRTH_YEAR_DROPDOWN = By.xpath("//select[@id='ageYear']");
    public static final By VIEW_PAGE_BUTTON = By.xpath("//div[@class='agegate_text_container btns']/a[1]");

    public AgeConfirmationPage(WebDriver driver){
        this.driver = driver;
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
