package com.miamato;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertComparison extends BaseTest{

    static final String AMAZON_HOME_PAGE_URL = "https://www.amazon.co.uk/";
    static final String AMAZON_HOME_PAGE_TITLE = "Amazon.co.uk";

    @Test
    public void hardAssertExample(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);
        System.out.println("This is hard assert, baby");
    }

    @Test
    public void softAssertExample(){
        driver.navigate().to(AMAZON_HOME_PAGE_URL);
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, AMAZON_HOME_PAGE_TITLE);
        System.out.println("Soft like a cloud");
        softAssert.assertAll();  //required in order to fail test if asserts fail.
    }

}
