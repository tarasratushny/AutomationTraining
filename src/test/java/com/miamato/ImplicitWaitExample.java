package com.miamato;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ImplicitWaitExample extends BaseTest {


    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.co.uk/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more";
    static final String SEARCH_TERM = "Drills";

    static final String ACCEPT_COOKIES_BUTTON_XPATH = "//input[@id='sp-cc-accept']";
    static final String SEARCH_FIELD_XPATH = "//input[@id='twotabsearchtextbox11111']";


    @Test
    public void implicitWaitTest(){
        //defined globally for all further search operations
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);

        //executed straight away, as element can be located right after page load
        driver.findElement(By.xpath(ACCEPT_COOKIES_BUTTON_XPATH)).click();
        System.out.println("Accept cookies found already");

        //waits 20 seconds to fail (xpath is intentionally broken)
        driver.findElement(By.xpath(SEARCH_FIELD_XPATH)).sendKeys(SEARCH_TERM);

        softAssert.assertAll();
    }

}
