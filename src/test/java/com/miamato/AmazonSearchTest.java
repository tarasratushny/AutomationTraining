package com.miamato;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonSearchTest extends BaseTest{

    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.co.uk/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more";
    static final String SEARCH_TERM = "Drills";

    static final String ACCEPT_COOKIES_BUTTON_XPATH = "//input[@id='sp-cc-accept']";
    static final String SEARCH_FIELD_XPATH = "//input[@id='twotabsearchtextbox']";
    static final String SEARCH_BUTTON_XPATH = "//input[@id='nav-search-submit-button']";
    static final String SEARCH_RESULTS_BREADCRUMB_XPATH = "//*[contains(@class,'s-breadcrumb')]//span[@class='a-color-state a-text-bold']";
    static final String SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU_XPATH = "//div[@id='departments']//span[@class='a-size-base a-color-base']";
    static final int TARGET_DEPARTMENT_INDEX = 0;

    @Test
    public void basicAmazonProductSearch(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);   // you can also use driver.get("AMAZON_HOME_PAGE_URL");
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);
        driver.findElement(By.xpath(ACCEPT_COOKIES_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(SEARCH_FIELD_XPATH)).sendKeys(SEARCH_TERM);
        driver.findElement(By.xpath(SEARCH_BUTTON_XPATH)).click();
        Assert.assertTrue(driver.findElement(By.xpath(SEARCH_RESULTS_BREADCRUMB_XPATH)).isDisplayed());
        Assert.assertEquals(driver.findElements(By.xpath(SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU_XPATH)).get(TARGET_DEPARTMENT_INDEX).getText(),SEARCH_TERM);
    }
}
