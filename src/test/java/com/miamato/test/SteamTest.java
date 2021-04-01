package com.miamato.test;

import com.miamato.BaseTest;
import com.miamato.LogUtil;
import com.miamato.PropertyManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SteamTest extends BaseTest {

    private static final String URL = PropertyManager.getProperty("homepage.url");

    private static final String SEARCH_FIELD_XPATH = "//input[@id='store_nav_search_term']";
    private static final String SEARCH_RESULTS_XPATH = "//a[contains(@class,'search_result_row')]";

    private static final String PDP_RELEASE_DATE_XPATH = "//div[@class='date']";
    private static final String PDP_DEVELOPERS_LIST_XPATH = "//div[@id='developers_list']/child::a";
    private static final String PDP_PUBLISHERS_LIST_XPATH = "(//div[text()='Publisher:']/following::a)[1]";


    @Test
    public void steamFun(){
        openWebPage(URL);
        enterTextIntoField(SEARCH_FIELD_XPATH, "Cyberpunk");
        pressKey(Keys.ENTER);
    }

    @Step
    private static void pressKey(Keys key){
        logger.info(SteamTest.class.getName() + " Pressing key: " + key.name());
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    @Step
    private static void enterTextIntoField(String xpath, String text){
        logger.info(SteamTest.class.getName() + "Entering text: \"" + text + "\" into field with XPath: " + xpath);
        Actions actions = new Actions(driver);
        actions.sendKeys(findElement(xpath),text).perform();
    }

    @Step
    private static void openWebPage(String url){
        logger.info(SteamTest.class.getName() + " ---- Navigating to website with url:   " + url);
        driver.navigate().to(url);
    }

    private static WebElement findElement(String xpath){
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            logger.error(SteamTest.class.getName() + " ---- Element with XPath: " + xpath + "  was not found on the page." );
            LogUtil.logStackTrace(e, logger);
        }
        return element;
    }
}
