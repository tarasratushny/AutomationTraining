package com.miamato.examples;

import com.miamato.BaseTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExplicitWaitExample extends BaseTest {


    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.co.uk/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more";
    static final String SEARCH_TERM = "Drills";

    static final String ACCEPT_COOKIES_BUTTON_XPATH = "//input[@id='sp-cc-accept']";
    static final String SEARCH_FIELD_XPATH = "//input[@id='twotabsearchtextbox11111']";


    @Test
    public void explicitWaitSleep() throws InterruptedException {
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);

        //Waits 10 seconds once. Recommended not to use it at all :)
        Thread.sleep(10000);
        driver.findElement(By.xpath(ACCEPT_COOKIES_BUTTON_XPATH)).click();
        System.out.println("Button was pressed, prepare for failing");

        //fails right away, as xpath is broken
        driver.findElement(By.xpath(SEARCH_FIELD_XPATH)).sendKeys(SEARCH_TERM);

        softAssert.assertAll();
    }

    @Test
    public void explicitWaitExplicitSimpleCondition(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);

        waitForElementToBePresent(ACCEPT_COOKIES_BUTTON_XPATH, 10).click();
        System.out.println("Button was pressed, prepare for failing");

        //fails right away, as xpath is broken
        waitForElementToBePresent(SEARCH_FIELD_XPATH, 20).sendKeys(SEARCH_TERM);

        softAssert.assertAll();
    }

    private static WebElement waitForElementToBePresent(String xPath, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    @Test
    public void explicitWaitExplicitFluent(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);

        waitForElementToBePresentFluent(ACCEPT_COOKIES_BUTTON_XPATH, 10, 3).click();
        System.out.println("Button was pressed, prepare for failing");

        //fails right away, as xpath is broken
        waitForElementToBePresentFluent(SEARCH_FIELD_XPATH, 20, 5).sendKeys(SEARCH_TERM);

        softAssert.assertAll();
    }

    private static WebElement waitForElementToBePresentFluent(String xPath, long timeoutSeconds, long intervalSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds), Duration.ofSeconds(intervalSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

}
