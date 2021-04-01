package com.miamato.test;

import com.miamato.BaseTest;
import com.miamato.LogUtil;
import com.miamato.PropertyManager;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SteamTest extends BaseTest {

    private static final String URL = PropertyManager.getProperty("homepage.url");
    private static final String CUSTOMER_BIRTH_DAY = PropertyManager.getProperty("birth.day");
    private static final String CUSTOMER_BIRTH_MONTH = PropertyManager.getProperty("birth.month");
    private static final String CUSTOMER_BIRTH_YEAR = PropertyManager.getProperty("birth.year");

    private static final String GAME_WITH_DISCOUNT_TITLE = PropertyManager.getProperty("game.with.discount.title");
    private static final int GAME_WITH_DISCOUNT_POSITION_IN_SEARCH = Integer.parseInt(PropertyManager.getProperty("game.with.discount.position.in.search"));
    private static final String GAME_WITH_DISCOUNT_DEVELOPER = PropertyManager.getProperty("game.with.discount.developer");
    private static final String GAME_WITH_DISCOUNT_PUBLISHER = PropertyManager.getProperty("game.with.discount.publisher");
    private static final String GAME_WITH_DISCOUNT_RELEASE_DATE = PropertyManager.getProperty("game.with.discount.release.date");
    private static final String GAME_WITH_DISCOUNT_ORIGINAL_PRICE = PropertyManager.getProperty("game.with.discount.original.price");


    private static final String SEARCH_FIELD_XPATH = "//input[@id='store_nav_search_term']";
    private static final String SEARCH_RESULTS_XPATH = "//a[contains(@class,'search_result_row')]";

    private static final String PDP_RELEASE_DATE_XPATH = "//div[@class='date']";
    private static final String PDP_DEVELOPERS_LIST_XPATH = "//div[@id='developers_list']/child::a";
    private static final String PDP_PUBLISHERS_LIST_XPATH = "(//div[text()='Publisher:']/following::a)[1]";
    private static final String PDP_MAIN_PRODUCT_ORIGINAL_PRICE = "//div[@class='game_area_purchase_game']//div[@class='discount_original_price']";

    private static final String AGE_CONFIRMATION_PAGA_DAY_DROPDOWN_XPATH = "//select[@id='ageDay']";
    private static final String AGE_CONFIRMATION_PAGA_MONTH_DROPDOWN_XPATH = "//select[@id='ageMonth']";
    private static final String AGE_CONFIRMATION_PAGA_YEAR_DROPDOWN_XPATH = "//select[@id='ageYear']";
    private static final String AGE_CONFIRMATION_PAGA_VIEW_PAGE_BUTTON_XPATH = "//div[@class='agegate_text_container btns']/a[1]";




    @Test
    public void addDiscountedProductToBasketWithDetailsCheck(){
        openWebPage(URL);
        enterTextIntoField(SEARCH_FIELD_XPATH, GAME_WITH_DISCOUNT_TITLE);
        pressKey(Keys.ENTER);
        clickOnElement(SEARCH_RESULTS_XPATH, GAME_WITH_DISCOUNT_POSITION_IN_SEARCH);
        selectFromDropdownByValue(AGE_CONFIRMATION_PAGA_DAY_DROPDOWN_XPATH, CUSTOMER_BIRTH_DAY);
        selectFromDropdownByValue(AGE_CONFIRMATION_PAGA_MONTH_DROPDOWN_XPATH, CUSTOMER_BIRTH_MONTH);
        selectFromDropdownByValue(AGE_CONFIRMATION_PAGA_YEAR_DROPDOWN_XPATH, CUSTOMER_BIRTH_YEAR);
        clickOnElement(AGE_CONFIRMATION_PAGA_VIEW_PAGE_BUTTON_XPATH);
        assertThatTextIsPresentInField(PDP_RELEASE_DATE_XPATH, GAME_WITH_DISCOUNT_RELEASE_DATE);
        assertThatTextIsPresentInField(PDP_DEVELOPERS_LIST_XPATH, GAME_WITH_DISCOUNT_DEVELOPER);
        assertThatTextIsPresentInField(PDP_PUBLISHERS_LIST_XPATH, GAME_WITH_DISCOUNT_PUBLISHER);
        assertThatTextIsPresentInField(PDP_MAIN_PRODUCT_ORIGINAL_PRICE, GAME_WITH_DISCOUNT_ORIGINAL_PRICE);
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
        logger.info(SteamTest.class.getName() + " Navigating to website with url:   " + url);
        driver.navigate().to(url);
    }

    @Step
    private static void clickOnElement(String xpath){
        clickOnElement(xpath, -1);
    }

    @Step
    private static void clickOnElement(String xpath, int position){
        logger.info(SteamTest.class.getName() + " Clicking on elment with xpath: " + xpath);
        Actions actions = new Actions(driver);
        actions.click(findElement(xpath, position)).perform();
    }

    @Step
    private static void selectFromDropdownByValue(String xpath, String value){
        logger.info(SteamTest.class.getName() + " Trying to select option: \"" + value + "\" from dropdown with XPath: " + xpath );
        Select dropdown = new Select(findElement(xpath));
        try {
            dropdown.selectByValue(value);
        } catch (Exception e) {
            logger.error(SteamTest.class.getName() + " Option cannot be selected from dropdown");
            LogUtil.logStackTrace(e, logger);
            throw e;
        }
    }

    @Step
    private static void assertThatTextIsPresentInField(String xpath, String text) {
        logger.info(SteamTest.class.getName() + " Checking that text: \"" + text
            + "\" is present in field with XPath:  " + xpath);
        try{
            Assert.assertEquals(findElementWithWait(xpath).getText(), text);
            logger.info("Text is equal to expected");
        } catch (Exception e) {
            logger.error(SteamTest.class.getName() + e.getLocalizedMessage());
            LogUtil.logStackTrace(e, logger);
            throw e;
        }
    }





    private static WebElement findElement(String xpath){
        return findElement(xpath, -1);
    }

    private static WebElement findElement(String xpath, int position){
        WebElement element = null;
        try{
            if(position == -1)
                element = driver.findElement(By.xpath(xpath));
            else
                element = driver.findElements(By.xpath(xpath)).get(position-1);
        } catch (Exception e) {
            logger.error(SteamTest.class.getName() + " Element with XPath: " + xpath + "  was not found on the page." );
            LogUtil.logStackTrace(e, logger);
        }
        return element;
    }



    private static WebElement findElementWithWait(String xpath){
        WebElement element = null;
        try{
            element = new WebDriverWait(driver, Duration.ofSeconds(3), Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            logger.error(SteamTest.class.getName() + " ---- Element with XPath: " + xpath + "  was not found on the page." );
            LogUtil.logStackTrace(e, logger);
        }
        return element;
    }
}
