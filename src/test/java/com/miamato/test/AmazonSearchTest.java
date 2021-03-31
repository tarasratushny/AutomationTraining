package com.miamato.test;

import com.miamato.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmazonSearchTest extends BaseTest {

    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.co.uk/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more";
    static final String SEARCH_TERM_DRILLS = "Drills";
    static final String SEARCH_TERM_SAWS = "Handsaws";
    static final String SEARCH_RESULT_FIRST_SAWS_DEPARTMENT = "Saws & Accessories";

    static final String ACCEPT_COOKIES_BUTTON_XPATH = "//input[@id='sp-cc-accept']";
    static final String SEARCH_FIELD_XPATH = "//input[@id='twotabsearchtextbox']";
    static final String SEARCH_BUTTON_XPATH = "//input[@id='nav-search-submit-button']";
    static final String SEARCH_RESULTS_BREADCRUMB_XPATH = "//*[contains(@class,'s-breadcrumb')]//span[@class='a-color-state a-text-bold']";
    static final String SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU_XPATH = "//div[@id='departments']//span[@class='a-size-base a-color-base']";
    static final int TARGET_DEPARTMENT_INDEX = 0;


    @DataProvider(name = "search-term-set")
    public Object[][] searchTerms() {
        return new Object[][]
            {{SEARCH_TERM_DRILLS, SEARCH_TERM_DRILLS}
          //  ,{SEARCH_TERM_SAWS, SEARCH_TERM_SAWS}
            ,{SEARCH_TERM_SAWS, SEARCH_RESULT_FIRST_SAWS_DEPARTMENT}};
    }


    @Test(dataProvider = "search-term-set")
    public void basicAmazonProductSearch(String searchTerm, String expectedDepartmentName){
        driver.get().navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.get().getTitle();
        Assert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);
        acceptCookiesIfPopupPresent();
        driver.get().findElement(By.xpath(SEARCH_FIELD_XPATH)).sendKeys(searchTerm);
        driver.get().findElement(By.xpath(SEARCH_BUTTON_XPATH)).click();
        Assert.assertTrue(driver.get().findElement(By.xpath(SEARCH_RESULTS_BREADCRUMB_XPATH)).isDisplayed());
        Assert.assertEquals(driver.get().findElements(By.xpath(SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU_XPATH)).get(TARGET_DEPARTMENT_INDEX).getText(),expectedDepartmentName);
    }

    private static void acceptCookiesIfPopupPresent(){
        try{
            driver.get().findElement(By.xpath(ACCEPT_COOKIES_BUTTON_XPATH)).click();
        } catch(NoSuchElementException e) {
            logger.info("Cookies pop-up is not present on page");
        }
    }
}
